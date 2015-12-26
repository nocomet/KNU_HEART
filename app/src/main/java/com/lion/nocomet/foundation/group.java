package com.lion.nocomet.foundation;

/**
 * Created by user on 2015-12-14.
 */
public class group {
    public String sid;
    public String dept;
    public String name1;
    public String name2;
    public String name3;
    public String line;
    public char sex;
    public group(String sid, String dept, String name1, String name2, String name3, String line, char sex)
    {
        this.sid=sid;
        this.dept=dept;
        this.name1=name1;
        this.name2=name2;
        this.name3=name3;
        this.line=line;
        this.sex=sex;
    }
    @Override
    public String toString()
    {
        String result="";
        result+="sid="+sid+" dept="+dept+" name1="+name1+" name2"+name2+" name3"+name3+" line"+line+" sex"+sex;
        return result;
    }

}
