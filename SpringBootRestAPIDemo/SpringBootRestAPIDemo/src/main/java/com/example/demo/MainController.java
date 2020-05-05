package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.MemberService;
import com.example.vo.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
public class MainController {
	@Autowired 
	private MemberService memberService;
	
	@PostMapping("/members")
	public Map register(@RequestBody MemberVO member) {
		//log.info("member = " + member);
		this.memberService.insertMember(member);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "success");
		return map;
	}
	
	@GetMapping("/members")
	public Map members() {
		Map<String, Object> map = new HashMap<String, Object>();
		this.memberService.selectAllMembers(map);
		List<MemberVO> list = (List<MemberVO>)map.get("results");
		list.forEach(member -> log.info("" + member));
		map.put("code", "success");
		return map;	
	}
	
	@GetMapping("/members/{userid}")
	public Map display(@PathVariable("userid") String userid) {
		log.info("userid = {}", userid);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", userid);
		this.memberService.selectMember(map);
		List<MemberVO> list = (List<MemberVO>)map.get("result");
		MemberVO member = list.get(0);
		map.remove("userid");
		map.remove("result");
		map.put("code", "success");
		map.put("result", member);
		return map;
	}
	
	//@DeleteMapping("/members/{userid}")
	@RequestMapping(value = "/members/{userid}", method=RequestMethod.DELETE)
	public Map delete(@PathVariable("userid") String userid) {
		log.info("삭제할 계정 : {}", userid);
		this.memberService.deleteMember(userid);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", "success");
		return map;
	}
	
	
	/*
	 * 
	 * 
	 * @GetMapping("/") public String index() { return "index";
	 * //templates/index.html }
	 * 
	 * @GetMapping("/register") public String register() { return "register";
	 * //templates/register.html }
	 * 
	 * @PostMapping("/register") public String register(MemberVO member) {
	 * //log.info("member = {}", member); this.memberService.insertMember(member);
	 * return "redirect:/list"; }
	 * 
	 * @GetMapping("/list") public String list(Model model) { Map<String, Object>
	 * map = new HashMap<String, Object>();
	 * this.memberService.selectAllMembers(map); List<MemberVO> list =
	 * (List<MemberVO>)map.get("results"); //list.forEach(member -> log.info("" +
	 * member)); model.addAttribute("members", list); return "list";
	 * //templates/list.html }
	 * 
	 * @GetMapping("/member/{userid}") public String display(@PathVariable String
	 * userid, Model model) { Map<String, Object> map = new HashMap<String,
	 * Object>(); map.put("userid", userid); this.memberService.selectMember(map);
	 * List<MemberVO> list = (List<MemberVO>)map.get("result"); MemberVO member =
	 * list.get(0); model.addAttribute("member", member); //log.info("user = {}",
	 * member); return "display"; //templates/display.html }
	 * 
	 * @RequestMapping(value="/delete/{userid}", method=RequestMethod.GET) public
	 * String delete(@PathVariable String userid) {
	 * this.memberService.deleteMember(userid); return "redirect:/"; }
	 * 
	 * @GetMapping("/member/update/{userid}") public String update(@PathVariable
	 * String userid, Model model) { Map<String, Object> map = new HashMap<String,
	 * Object>(); map.put("userid", userid); this.memberService.selectMember(map);
	 * List<MemberVO> list = (List<MemberVO>)map.get("result"); MemberVO member =
	 * list.get(0); model.addAttribute("member", member); //log.info("수정할 UserID = "
	 * + userid); return "update"; //templates/update.html }
	 * 
	 * @RequestMapping(value = "/member", method=RequestMethod.POST) public String
	 * update(MemberVO member) { //log.info("수정할 멤버 = " + member);
	 * this.memberService.updateMember(member); return "redirect:/member/" +
	 * member.getUserid(); }
	 */
}





