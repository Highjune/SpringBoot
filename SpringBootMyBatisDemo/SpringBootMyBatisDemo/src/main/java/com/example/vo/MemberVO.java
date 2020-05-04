package com.example.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor 
@ToString
@NoArgsConstructor
public class MemberVO {
	@NonNull private String userid;
	private String passwd;
	@NonNull private String name;
	@NonNull private int age;
	@NonNull private String gender;
	@NonNull private String city;
}

//RequiredArgsConstructor는 passwd를 제외한 생성자
