package com.example.testboard;

import com.example.testboard.dto.MemberDto;
import com.example.testboard.mapper.MemberMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MapperTests {

    @Autowired
    private MemberMapper memberMapper;

    @Test
    public void testInsert() {

        MemberDto memberDto = new MemberDto();
        memberDto.setName("장발장");
        memberDto.setId("mr.bal");
        memberDto.setPhone("010-111-2222");

        System.out.println(memberDto);
        memberMapper.insertMember(memberDto);

        System.out.println("--------------------------------------");
        System.out.println("레코드가 추가되었습니다.");
        System.out.println("--------------------------------------");
    }

}
