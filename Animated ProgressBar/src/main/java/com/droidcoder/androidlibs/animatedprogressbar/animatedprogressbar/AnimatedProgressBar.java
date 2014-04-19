/*
    Copyright (c) 2014, Athanasios Karpouzis (http://droid-coder.com)  All rights reserved.
    Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
    1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
    2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
    3. Neither the name of the copyright holder nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
    THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package com.droidcoder.androidlibs.animatedprogressbar.animatedprogressbar;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * Created by athanasioskarpouzis on 4/19/14.
 */
public class AnimatedProgressBar extends ProgressBar{

    private boolean animating = false;

    private TimerTickListener myListener;


    public AnimatedProgressBar(Context context, AttributeSet attrs){
        super(context, attrs, android.R.style.Widget_ProgressBar_Horizontal);
    }

    private void init(){

        if(!isInEditMode()){

        }
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startDefaultAnimation();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopDefaultAnimation();
    }

    public void startDefaultAnimation(){
        if(animating){
            return;
        }
        animating = true;
        myListener = new TimerTickListener() {
            @Override
            public void onTimerTick() {
                setProgress(getProgress()+1);
                if(getProgress() >= getMax()){
                    stopDefaultAnimation();
                }
            }
        };
        AnimationTimer.getInstance().addListener(myListener);
    }

    public void stopDefaultAnimation(){
        if(!animating){
            return;
        }
        animating = false;
        AnimationTimer.getInstance().removeListener(myListener);
    }


    public boolean isAnimating() {
        return animating;
    }
}
