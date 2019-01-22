package com.phil;

import javassist.*;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


public class JavassistTest {
    @Ignore
    @Test
    public void updateMethod() throws NotFoundException, CannotCompileException, IOException {
        ClassPool pool = new ClassPool();
        pool.appendSystemPath();
        CtClass ctl = pool.get("com.tuling.demo.test.UserServiceImpl");
        CtMethod mehod = ctl.getDeclaredMethod("getUser");
        mehod.insertBefore("System.out.println(\"abc\");");
        CtField f = new CtField(pool.get(String.class.getName()), "abc", ctl);
        ctl.addField(f);
        File file = new File(System.getProperty("user.dir") + "/target/UserServiceImpl.class");
        file.createNewFile();
        Files.write(file.toPath(), ctl.toBytecode());
    }

    @Ignore
    @Test
    public void update() throws NotFoundException, CannotCompileException, IOException {
        ClassPool pool = new ClassPool();
        pool.appendSystemPath();
        CtClass ctl = pool.get("com.tuling.demo.test.UserServiceImpl");
        CtMethod mehod = ctl.getDeclaredMethod("addUser");
//        mehod.insertBefore("System.out.println($0);");
//        mehod.insertBefore("System.out.println($1);");
//        mehod.insertBefore("System.out.println($2);");
        mehod.insertBefore(" addUser2($$);");
        ctl.toClass();
        new UserServiceImpl().addUser("luban", "man");
    }
}


