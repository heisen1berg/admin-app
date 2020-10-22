package core.gui;


import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CustomLogger {
    @Resource
    LogConsole logConsole;

    public void info(String line,int type){
        logConsole.log(new LogLine(line,type));
    }
    public void warning(String line){
        logConsole.log(new LogLine(line,LogLine.TYPE_ERROR));
    }
}
