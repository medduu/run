package com.example.running.member;

import com.example.running.member.domain.Member;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MemberTest {

    @Test
    public void 멤버객체테스트() {
        Member member = MemberBuilder.memberBuild ();
        Member admin = MemberBuilder.adminBuild ();

        assertThat(member.getUsername (), is("testMember"));
        assertThat(member.getPassword (), is("1111"));
        assertThat(member.getName (), is("member"));
        assertThat(member.getRoles ().size (), is(1));

        assertThat(admin.getUsername (), is("testAdmin"));
        assertThat(admin.getPassword (), is("1234"));
        assertThat(admin.getName (), is("admin"));
        assertThat(admin.getRoles ().size (), is(2));
    }
}
