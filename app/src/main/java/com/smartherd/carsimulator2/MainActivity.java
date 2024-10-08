package com.smartherd.carsimulator2;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private int activeColor, inactiveColor;
    private ImageView iconLock, iconBattery, iconTemp, iconTire;
    private boolean isIcon = true; //to help in toggling between icons
    private boolean isIcon2 = true;
    private boolean isIcon3 = true;
    private boolean isIcon4 = true;
    private boolean isIcon5 = true;
    private boolean isIcon6 = true;
    private ImageView lockClose1;
    private ImageView lockClose2;
    private ImageView lockClose3;
    private ImageView lockClose4;
    private boolean locksVisible = true;
    private boolean othersVisible = true;  // Flag to track visibility of other elements
    private LinearLayout carPercentage;
    private ImageView ceilingBack;
    private TextView carPercentageInfo;
    private TextView carSpeed;
    private TextView carVolt;
    private ImageView mainCar;
    private boolean isHalfVisible = false; // Boolean to check car's position\
    private ImageView coolIcon;
    private ImageView heatIcon;
    private LinearLayout tempSpinner;
    private TextView temperatureText;
    private ImageButton increaseTemp, decreaseTemp;
    private int currentTemp = 29; // Default temperature
    private LinearLayout bodyTemps;
    private TextView tempText;
    LinearLayout tempButtons;
    private RelativeLayout pressureLayout1;
    private RelativeLayout pressureLayout2;
    private RelativeLayout pressureLayout3;
    private RelativeLayout pressureLayout4;
    private boolean isVisible =false; //toggle visibile for the pressureLayouts
    private boolean[] iconStates = new boolean[4]; // Four icons, so array of size 4
    private ImageView carTire1;
    private ImageView carTire2;
    private ImageView carTire3;
    private ImageView carTire4;
    private String activeIconName = ""; //Variable to store the active icon name... the state



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

//        // Inside your activity's onCreate method or wherever needed
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.setStatusBarColor(ContextCompat.getColor(this, R.color.black)); // Use your desired color
//
//        }

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);



        activeColor = ContextCompat.getColor(this, R.color.active_blue); // Active color (blue)
        inactiveColor = ContextCompat.getColor(this, R.color.inactive_grey); // Inactive color (grey)

        //load the animation from the XML resource -- float_in
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.float_in);

        carPercentage = findViewById(R.id.car_percentage);
        carPercentageInfo = findViewById(R.id.car_percentage_info);
        ceilingBack = findViewById(R.id.ceiling_back);
        carSpeed = findViewById(R.id.car_speed);
        carVolt = findViewById(R.id.car_voltage);
        mainCar = findViewById(R.id.mainCar);
        coolIcon = findViewById(R.id.cool_inactive);
        heatIcon = findViewById(R.id.heat_inactive);
        tempSpinner = findViewById(R.id.tempSpinner);
        bodyTemps = findViewById(R.id.body_temp);
        tempText = findViewById(R.id.current_temperature_text);
        tempButtons = findViewById(R.id.temp_buttons);
        pressureLayout1 = findViewById(R.id.pressureLayout1);
        pressureLayout2 = findViewById(R.id.pressureLayout2);
        pressureLayout3 = findViewById(R.id.pressureLayout3);
        pressureLayout4 = findViewById(R.id.pressureLayout4);
        carTire1 = findViewById(R.id.tire1);
        carTire2 = findViewById(R.id.tire2);
        carTire3 = findViewById(R.id.tire3);
        carTire4 = findViewById(R.id.tire4);

        lockClose1 = findViewById(R.id.lock1);
        lockClose2 = findViewById(R.id.lock2);
        lockClose3 = findViewById(R.id.lock3);
        lockClose4 = findViewById(R.id.lock4);


        iconBattery = findViewById(R.id.icon_battery);
        iconLock = findViewById(R.id.icon_lock);
        iconTemp = findViewById(R.id.icon_temperature);
        iconTire = findViewById(R.id.icon_tire);


        temperatureText = findViewById(R.id.temperatureText);
        increaseTemp = findViewById(R.id.increaseTemp);
        decreaseTemp = findViewById(R.id.decreaseTemp);

        // Set initial temperature
        updateTemperatureText();


        lockClose1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isIcon){
                    lockClose1.setImageResource(R.drawable.lock_open);
                    isIcon = false;
                }else {
                    lockClose1.setImageResource(R.drawable.lock_close);
                    isIcon = true;
                }
                isIcon = isIcon; //toggle the flag
            }
        });

        lockClose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isIcon2){
                    lockClose2.setImageResource(R.drawable.lock_open);
                    isIcon2 = false;
                }else {
                    lockClose2.setImageResource(R.drawable.lock_close);
                    isIcon2 = true;
                }
                isIcon2 = isIcon2; //toggle the flag
            }
        });

        lockClose3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isIcon3){
                    lockClose3.setImageResource(R.drawable.lock_open);
                    isIcon3 = false;
                }else {
                    lockClose3.setImageResource(R.drawable.lock_close);
                    isIcon3 = true;
                }
                isIcon3 = isIcon3; //toggle the flag
            }
        });

        lockClose4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isIcon4){
                    lockClose4.setImageResource(R.drawable.lock_open);
                    isIcon4 = false;
                }else {
                    lockClose4.setImageResource(R.drawable.lock_close);
                    isIcon4 = true;
                }
                isIcon4 = isIcon4; //toggle the flag
            }
        });


        coolIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Animate the image to increase in size
                animateIcon(coolIcon);
//                if(isIcon5){
//                    coolIcon.setImageResource(R.drawable.cool_active);
//                    mainCar.setImageResource(R.drawable.cool_car);
//                    isIcon5 = false;
//                }else {
//                    coolIcon.setImageResource(R.drawable.cool_inactive);
//                    mainCar.setImageResource(R.drawable.car);
//                    isIcon5 = true;
//                }
                if (isIcon5) {
                    // Fade out the current icon
                    coolIcon.animate().alpha(0f).setDuration(200).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            // Set the new drawable
                            coolIcon.setImageResource(R.drawable.cool_active);
                            // Fade in the new drawable
                            coolIcon.animate().alpha(1f).setDuration(200).start();
                        }
                    }).start();

                    // Same for the car icon
                    mainCar.animate().alpha(0f).setDuration(200).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            mainCar.setImageResource(R.drawable.cool_car);
                            mainCar.animate().alpha(1f).setDuration(200).start();
                        }
                    }).start();

                    isIcon5 = false;
                } else {
                    // Fade out the current icon
                    coolIcon.animate().alpha(0f).setDuration(200).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            // Set the new drawable
                            coolIcon.setImageResource(R.drawable.cool_inactive);
                            // Fade in the new drawable
                            coolIcon.animate().alpha(1f).setDuration(200).start();
                        }
                    }).start();

                    // Same for the car icon
                    mainCar.animate().alpha(0f).setDuration(200).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            mainCar.setImageResource(R.drawable.car);
                            mainCar.animate().alpha(1f).setDuration(200).start();
                        }
                    }).start();

                    isIcon5 = true;
                }


                isIcon5 = isIcon5; //toggle the flag
            }
        });

        heatIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Animate the image to increase in size
                animateIcon(heatIcon);
//                if(isIcon6){
//                    heatIcon.setImageResource(R.drawable.heat_active);
//                    mainCar.setImageResource(R.drawable.heat_car);
//                    isIcon6 = false;
//                }else {
//                    heatIcon.setImageResource(R.drawable.heat_inactive);
//                    mainCar.setImageResource(R.drawable.car);
//                    isIcon6 = true;
//                }
                if (isIcon5) {
                    // Fade out the current icon
                    heatIcon.animate().alpha(0f).setDuration(200).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            // Set the new drawable
                            heatIcon.setImageResource(R.drawable.heat_active);
                            // Fade in the new drawable
                            heatIcon.animate().alpha(1f).setDuration(200).start();
                        }
                    }).start();

                    // Same for the car icon
                    mainCar.animate().alpha(0f).setDuration(200).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            mainCar.setImageResource(R.drawable.heat_car);
                            mainCar.animate().alpha(1f).setDuration(200).start();
                        }
                    }).start();

                    isIcon5 = false;
                } else {
                    // Fade out the current icon
                    heatIcon.animate().alpha(0f).setDuration(200).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            // Set the new drawable
                            heatIcon.setImageResource(R.drawable.heat_inactive);
                            // Fade in the new drawable
                            heatIcon.animate().alpha(1f).setDuration(200).start();
                        }
                    }).start();

                    // Same for the car icon
                    mainCar.animate().alpha(0f).setDuration(200).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            mainCar.setImageResource(R.drawable.car);
                            mainCar.animate().alpha(1f).setDuration(200).start();
                        }
                    }).start();

                    isIcon5 = true;
                }
                isIcon6 = isIcon6; //toggle the flag
            }
        });

        increaseTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentTemp < 40) { // Max temperature is 40°C
                    currentTemp++;
                    updateTemperatureText();
                }
            }
        });

        decreaseTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentTemp > 10) { // Min temperature is 10°C
                    currentTemp--;
                    updateTemperatureText();
                }
            }
        });



        iconLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setActiveIcon(iconLock, "Lock Icon");
                if (activeIconName.equals("Battery Icon")){
                    Log.d("MainActivity", "Battery icon was active");
                }
                showLocks();
                hideOthers();
                hideCarTemps();
                hideTireStates();

                mainCar.setImageResource(R.drawable.car);


                // Get the width of the screen and the car's width
                mainCar.post(new Runnable() {
                    @Override
                    public void run() {
                        int screenWidth = getResources().getDisplayMetrics().widthPixels;
                        int carWidth = mainCar.getWidth();


                            // Move back to the original position
                            ObjectAnimator animator = ObjectAnimator.ofFloat(mainCar, "translationX", 0);
                            animator.setDuration(1000);
                            animator.start();
                            isHalfVisible = false; // Update the state
                    }
                });
            }
        });

        iconBattery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setActiveIcon(iconBattery, "Battery Icon");
                hideLocks();
                showOthers();
                hideCarTemps();
                hideTireStates();
                mainCar.setImageResource(R.drawable.car);
                // Get the width of the screen and the car's width
                mainCar.post(new Runnable() {
                    @Override
                    public void run() {
                        int screenWidth = getResources().getDisplayMetrics().widthPixels;
                        int carWidth = mainCar.getWidth();


                        // Move back to the original position
                        ObjectAnimator animator = ObjectAnimator.ofFloat(mainCar, "translationX", 0);
                        animator.setDuration(1000);
                        animator.start();
                        isHalfVisible = false; // Update the state
                    }
                });
            }
        });

        iconTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setActiveIcon(iconTemp, "Temp Icon");
                hideLocks();
                hideOthers();
                showCarTemps();
                hideTireStates();
                mainCar.setImageResource(R.drawable.car);
                // Get the width of the screen and the car's width
                mainCar.post(new Runnable() {
                    @Override
                    public void run() {
                        int screenWidth = getResources().getDisplayMetrics().widthPixels;
                        int carWidth = mainCar.getWidth();

                        // Animate the car to move to the right until only half is visible
                        ObjectAnimator animator = ObjectAnimator.ofFloat(mainCar, "translationX", screenWidth - (carWidth / 2));
                        animator.setDuration(1000); // Animation duration 1 second
                        animator.start();
                    }
                });
            }
        });

        iconTire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setActiveIcon(iconTire, "Tire Icon");
                hideLocks();
                hideCarTemps();
                hideOthers();
                showTireStates();
                mainCar.setImageResource(R.drawable.car);

                // Get the width of the screen and the car's width
                mainCar.post(new Runnable() {
                    @Override
                    public void run() {
                        int screenWidth = getResources().getDisplayMetrics().widthPixels;
                        int carWidth = mainCar.getWidth();


                        // Move back to the original position
                        ObjectAnimator animator = ObjectAnimator.ofFloat(mainCar, "translationX", 0);
                        animator.setDuration(1000);
                        animator.start();
                        isHalfVisible = false; // Update the state
                    }
                });

            }
        });



    }

    private void showTireStates() {
//        pressureLayout1.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in));
//        pressureLayout2.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in));
//        pressureLayout3.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in));
//        pressureLayout4.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in));
        if (!isVisible) {
            animateFramesIn();
        }
        isVisible = !isVisible;
        carTire1.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
        carTire2.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
        carTire3.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
        carTire4.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));

        pressureLayout1.setVisibility(View.VISIBLE);
        pressureLayout2.setVisibility(View.VISIBLE);
        pressureLayout3.setVisibility(View.VISIBLE);
        pressureLayout4.setVisibility(View.VISIBLE);
        carTire1.setVisibility(View.VISIBLE);
        carTire2.setVisibility(View.VISIBLE);
        carTire3.setVisibility(View.VISIBLE);
        carTire4.setVisibility(View.VISIBLE);
    }

    private void animateFramesIn() {
        animateFrame(pressureLayout1, 0);      // Immediate start for the first Frame
        animateFrame(pressureLayout2, 500);    // Start after 500ms
        animateFrame(pressureLayout3, 1000);   // Start after 1000ms
        animateFrame(pressureLayout4, 1500);   // Start after 1500ms
    }

    private void animateFrame(final RelativeLayout frame, int delay) {
        frame.setVisibility(View.VISIBLE);
        // Create the floating "translate" animation (from outside of the screen into position)
        TranslateAnimation translate = new TranslateAnimation(0, 0, 1000, 0);
        translate.setDuration(700);  // How long it takes for the float in
        translate.setStartOffset(delay); // Delay before this frame starts the animation

        // Set animation to the FrameLayout
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(translate);
        frame.startAnimation(animationSet);
    }

    private void hideTireStates() {

        pressureLayout1.setVisibility(View.INVISIBLE);
        pressureLayout2.setVisibility(View.INVISIBLE);
        pressureLayout3.setVisibility(View.INVISIBLE);
        pressureLayout4.setVisibility(View.INVISIBLE);
        carTire1.setVisibility(View.INVISIBLE);
        carTire2.setVisibility(View.INVISIBLE);
        carTire3.setVisibility(View.INVISIBLE);
        carTire4.setVisibility(View.INVISIBLE);
    }

    // Method to update the temperature text
    private void updateTemperatureText() {
        String tempText = currentTemp + "°C";
        temperatureText.setText(tempText);
    }

    private void showCarTemps() {

        tempSpinner.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in));
        bodyTemps.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in));
        tempText.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in));
        tempButtons.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in));



        tempSpinner.setVisibility(View.VISIBLE);
        bodyTemps.setVisibility(View.VISIBLE);
        tempText.setVisibility(View.VISIBLE);
        tempButtons.setVisibility(View.VISIBLE);


    }

    private void hideCarTemps(){


        tempSpinner.setVisibility(View.INVISIBLE);
        bodyTemps.setVisibility(View.INVISIBLE);
        tempText.setVisibility(View.INVISIBLE);
        tempButtons.setVisibility(View.INVISIBLE);

    }

    private void animateIcon(ImageView imageView) {
        // Increase the scale by 1.2 times (20% increase)
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(imageView, "scaleX", 1f, 1.2f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(imageView, "scaleY", 1f, 1.2f);

        // Set the duration of the animation
        scaleX.setDuration(200);
        scaleY.setDuration(200);

        // Start the animations together
        scaleX.start();
        scaleY.start();

        // Optional: Add a listener to reset the scale back after animation
        scaleX.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // Reset the scale back to original
                ObjectAnimator resetScaleX = ObjectAnimator.ofFloat(imageView, "scaleX", 1.2f, 1f);
                ObjectAnimator resetScaleY = ObjectAnimator.ofFloat(imageView, "scaleY", 1.2f, 1f);

                // Set duration for reset animation
                resetScaleX.setDuration(200);
                resetScaleY.setDuration(200);

                // Start the reset animations
                resetScaleX.start();
                resetScaleY.start();
            }
        });
    }

    // Method to toggle the visibility of 'other' elements
    private void toggleOthers() {
        if (othersVisible) {
            hideOthers();  // If others are visible, hide them
        } else {
            showOthers();  // If others are hidden, show them
        }
        othersVisible = !othersVisible;  // Toggle the visibility state of 'other' elements
    }

    // Method to toggle locks and other elements
    private void toggleLocks() {
        if (locksVisible) {
            hideLocks();
            showOthers();
        }
        locksVisible = !locksVisible;  // Toggle the visibility state
    }

    // Show lock icons with animation
    private void showLocks() {
        lockClose1.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in));
        lockClose2.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in));
        lockClose3.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in));
        lockClose4.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in));

        lockClose1.setVisibility(View.VISIBLE);
        lockClose2.setVisibility(View.VISIBLE);
        lockClose3.setVisibility(View.VISIBLE);
        lockClose4.setVisibility(View.VISIBLE);
    }

    // Hide other elements (car and bottom layout) with fade-out animation
    private void hideOthers() {
//
        carPercentage.setVisibility(View.INVISIBLE);
        ceilingBack.setVisibility(View.INVISIBLE);
        carPercentageInfo.setVisibility(View.INVISIBLE);
        carSpeed.setVisibility(View.INVISIBLE);
        carVolt.setVisibility(View.INVISIBLE);
    }

    // Show other elements (car and bottom layout) with fade-in animation
    private void showOthers() {
        carPercentage.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
        ceilingBack.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
        carPercentageInfo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
        carSpeed.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
        carVolt.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
//
        carPercentage.setVisibility(View.VISIBLE);
        ceilingBack.setVisibility(View.VISIBLE);
        carPercentageInfo.setVisibility(View.VISIBLE);
        carSpeed.setVisibility(View.VISIBLE);
        carVolt.setVisibility(View.VISIBLE);
    }

    // Hide lock icons with animation
    private void hideLocks() {

        lockClose1.setVisibility(View.INVISIBLE);
        lockClose2.setVisibility(View.INVISIBLE);
        lockClose3.setVisibility(View.INVISIBLE);
        lockClose4.setVisibility(View.INVISIBLE);
    }

    private void setActiveIcon(ImageView activeIcon, String iconName) {
        // Reset all icons to inactive color
        iconLock.setColorFilter(inactiveColor);
        iconBattery.setColorFilter(inactiveColor);
        iconTemp.setColorFilter(inactiveColor);
        iconTire.setColorFilter(inactiveColor);

        // Set the active icon to active color
        activeIcon.setColorFilter(activeColor);

        activeIconName = iconName;
        Log.d("IconsStatus", activeIconName + "is active...");
    }
}