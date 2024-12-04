package com.example.testboard.mapper;

import com.example.testboard.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    public void insertMember(MemberDto memberDto);
}
