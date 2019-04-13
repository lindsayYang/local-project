package com.lindsay.test.test;

/**
 * ConcretePrototype
 *
 * @author Lindsay
 * @date 2019/4/9 17:01
 **/
public class ConcretePrototype implements Prototype {

    private String attr; //成员属性

    public void setAttr(String attr)
    {
        this.attr = attr;
    }

    public String getAttr()
    {
        return this.attr;
    }

    public Prototype clone()
    {
        Object object = null;
        try {
            object = super.clone();
        } catch (CloneNotSupportedException exception) {
            System.err.println("Not support cloneable");
        }

        return (Prototype) object;
    }

}
