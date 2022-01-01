package com.knits.kncare.dto;

public interface Views {


    public static interface RequestOnly {}
    public static interface Common {}
    public static interface GroupDetails {}
    public static interface GroupMembership extends Common{}
    public static interface GroupMembers extends Common{}
    public static interface EmployeeDetails extends Common{}
    public static interface MemberDetails extends Common{}
    public static interface EmailDetails extends Common {}
    public static interface EmailSentDetails extends Common {}
    public static interface FileDb extends Common{}

}
