package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.service.MemberService;
import com.example.vo.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller //모든 경로는 controller가 관리
public class MainController {  //실제로 할 때는 게시판, 방명록 등 컨트롤 다 구분하기. 왜냐하면 하나의 mainctonroller안에 다 넣으려면 함수 등 신경써야 될 것들이 많기 때문이다.
	@Autowired 
	private MemberService memberService; //Controller가 다 service에게 보내니까 Autowired. 이  memberService가 바로 service의 @Service("memberService")이다.
	
	@GetMapping("/") //get으로 "/"로 들어오면~
	public String index() {
		return "index";     //templates/index.html cf) 만약 확장자(.html)까지 붙이면 templates에서 찾는 것이 아니라 static에서 찾는다.
	}
	
	@GetMapping("/register") //url로 이렇게 들어오면~
	public String register() {
		return "register";      //templates/register.html
	}
	
	@PostMapping("/register") //post인 이유는 register.html의 form 메서드를 post로 설정
	public String register(MemberVO member) { //Commander1방식으로 data받음(html의 name과 클래스의 변수가 다 동일할 때)
		//log.info("member = {}", member); 
		this.memberService.insertMember(member); //받은 것을 그대로 서비스로 넘김(-> dao -> sqlsession -> mapper호출 -> stored procedure 호출)
		return "redirect:/list"; 
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		this.memberService.selectAllMembers(map);
		List<MemberVO> list = (List<MemberVO>)map.get("results");
		//list.forEach(member -> log.info("" + member));
		model.addAttribute("members", list);
		return "list";   //templates/list.html
	}
	
	@GetMapping("/member/{userid}")
	public String display(@PathVariable String userid, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", userid);
		this.memberService.selectMember(map);
		List<MemberVO> list = (List<MemberVO>)map.get("result");
		MemberVO member = list.get(0);
		model.addAttribute("member", member);
		//log.info("user = {}", member);
		return "display";   //templates/display.html
	}
	
	@RequestMapping(value="/delete/{userid}", method=RequestMethod.GET)
	public String delete(@PathVariable String userid) {
		this.memberService.deleteMember(userid); 
		return "redirect:/";
	}
	
	@GetMapping("/member/update/{userid}")
	public String update(@PathVariable String userid, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", userid);
		this.memberService.selectMember(map);
		List<MemberVO> list = (List<MemberVO>)map.get("result");
		MemberVO member = list.get(0);
		model.addAttribute("member", member);
		//log.info("수정할 UserID = " + userid);
		return "update";   //templates/update.html
	}
	
	 @RequestMapping(value = "/member", method=RequestMethod.POST) 
	 public String update(MemberVO member) {
		 //log.info("수정할 멤버 = " + member);
		 this.memberService.updateMember(member); 
		 return  "redirect:/member/" + member.getUserid();
	 }
}





