package com.example.teachingdemo.mvp;

import android.content.Intent;
import android.os.Bundle;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author sjc
 * @Date 2020/5/9.
 * GitHub：
 * Email：jiachen.sun@credit.com
 * Description：
 */
public class LifeCircleImpl implements ILifeCircle {

    /** 存放的是 P层的实例 */
    private Set<ILifeCircle> lifeCircles = new HashSet<>();

    public void savePresenter(ILifeCircle lifeCircle) {
        this.lifeCircles.add(lifeCircle);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, Intent intent, Bundle getArguments) {
        if (lifeCircles.size() > 0) {
            for (ILifeCircle iLifeCircle : lifeCircles) {
                if (intent == null) {
                    intent = new Intent();
                }
                if (getArguments == null) {
                    getArguments = new Bundle();
                }
                iLifeCircle.onCreate(savedInstanceState, intent, getArguments);
            }
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState, Intent intent, Bundle getArguments) {
        if (lifeCircles.size() > 0) {
            for (ILifeCircle iLifeCircle : lifeCircles) {
                if (intent == null) {
                    intent = new Intent();
                }
                if (getArguments == null) {
                    getArguments = new Bundle();
                }
                iLifeCircle.onActivityCreated(savedInstanceState, intent, getArguments);
            }
        }
    }

    @Override
    public void onStart() {
        if (lifeCircles.size() > 0) {
            for (ILifeCircle iLifeCircle : lifeCircles) {
                iLifeCircle.onStart();
            }
        }
    }

    @Override
    public void onResume() {
        if (lifeCircles.size() > 0) {
            for (ILifeCircle iLifeCircle : lifeCircles) {
                iLifeCircle.onResume();
            }
        }
    }

    @Override
    public void onPause() {
        if (lifeCircles.size() > 0) {
            for (ILifeCircle iLifeCircle : lifeCircles) {
                iLifeCircle.onPause();
            }
        }
    }

    @Override
    public void onStop() {
        if (lifeCircles.size() > 0) {
            for (ILifeCircle iLifeCircle : lifeCircles) {
                iLifeCircle.onStop();
            }
        }
    }

    @Override
    public void onReStart() {
        if (lifeCircles.size() > 0) {
            for (ILifeCircle iLifeCircle : lifeCircles) {
                iLifeCircle.onReStart();
            }
        }
    }

    @Override
    public void onDestroy() {
        if (lifeCircles.size() > 0) {
            for (ILifeCircle iLifeCircle : lifeCircles) {
                iLifeCircle.onDestroy();
            }
        }
    }

    @Override
    public void onViewDestroy() {
        if (lifeCircles.size() > 0) {
            for (ILifeCircle iLifeCircle : lifeCircles) {
                iLifeCircle.onViewDestroy();
            }
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        if (lifeCircles.size() > 0) {
            for (ILifeCircle iLifeCircle : lifeCircles) {
                iLifeCircle.onNewIntent(intent);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (lifeCircles.size() > 0) {
            for (ILifeCircle iLifeCircle : lifeCircles) {
                iLifeCircle.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        if (lifeCircles.size() > 0) {
            for (ILifeCircle iLifeCircle : lifeCircles) {
                iLifeCircle.onSaveInstanceState(bundle);
            }
        }
    }

    @Override
    public void attachView(IMvpView iMvpView) {
        if (lifeCircles.size() > 0) {
            for (ILifeCircle iLifeCircle : lifeCircles) {
                iLifeCircle.attachView(iMvpView);
            }
        }
    }
}
