package cn.com.fhz;

import cn.com.fhz.constant.Constant;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by woni on 18/1/28.
 */

public class ConstantTest extends TestCase{

    public void test01(){

        Constant create = Constant.CREATE;

        Integer code = create.getCode();

       String msg =  create.getMsg();

        System.out.println("code:\t"+code+"\tmsg:\t"+msg);


    }

}
