package com.library;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class MemberTest {
    @DataProvider(name = "memberData")
    public Object[][] memberData() {
        return new Object[][] {
            { "1", "Bisam" },
            { "2", "Haseeb" }
        };
    }

    @Test(dataProvider = "memberData")
    public void testMemberCreation(String memberId, String name) {
        Member member = new Member(memberId, name);
        assertEquals(member.getMemberId(), memberId);
        assertEquals(member.getName(), name);
    }
}