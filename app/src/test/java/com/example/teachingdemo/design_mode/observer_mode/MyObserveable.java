package com.example.teachingdemo.design_mode.observer_mode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author sjc
 * @Date 2020/7/27
 * Description：
 */
public class MyObserveable {
    private List<IMyOberver> obervers = new ArrayList<>();

    public void subject(IMyOberver oberver) {
        obervers.add(oberver);
    }

    public void notifyAllObserver() {
        if (obervers.size() > 0) {
            for (IMyOberver oberver : obervers) {
                oberver.start();
            }
        }
    }
}
