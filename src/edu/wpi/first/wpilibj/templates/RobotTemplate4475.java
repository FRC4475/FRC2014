/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate4475 extends SimpleRobot {
    AnalogChannel myArmpossicion;
    RobotDrive myDrive;
    Joystick moveStick, rotateStick,ballcontrolStick;
    Gyro gyro;
    Jaguar Frontmotor1, Frontmotor2;
    Jaguar Backmotor1, Backmotor2;
    Encoder FrontRightEncoder;
    Encoder FrontLeftEncoder;
    Encoder RearLeftEncoder;
    Encoder RearRightEncoder;
    
    public void robotInit() {
    FrontRightEncoder = new Encoder(1,2,true,CounterBase.EncodingType.k4X);
    FrontLeftEncoder = new Encoder(3,4,true,CounterBase.EncodingType.k4X);
    RearRightEncoder = new Encoder(5,6,true,CounterBase.EncodingType.k4X);
    RearLeftEncoder = new Encoder(7,8,true,CounterBase.EncodingType.k4X);
   
    Frontmotor1=new Jaguar(5);
    Frontmotor2=new Jaguar(6);
    Backmotor1=new Jaguar(7);
    Backmotor2=new Jaguar(8);
    myDrive = new RobotDrive(1, 2, 3, 4);
    moveStick = new Joystick(1);
    //rotateStick = new Joystick(2);
    ballcontrolStick = new Joystick(2);
    gyro = new Gyro(1);
    gyro.reset();
    myArmpossicion = new AnalogChannel(2);
    
    double angle = gyro.getAngle();
    SmartDashboard.putNumber("angle", angle);
    
    double Armpossicion =  myArmpossicion.getVoltage();
    SmartDashboard.putNumber("Armpossicion",Armpossicion );
   
    //Setup Encoders to Start
    FrontRightEncoder.start();
    FrontLeftEncoder.start();
    RearRightEncoder.start();
    RearLeftEncoder.start();
    
    //Display Encoders on Dashboard
    SmartDashboard.putNumber("Front Right Encoder", FrontRightEncoder.getDistance());
    SmartDashboard.putNumber("Front Left Encoder", FrontLeftEncoder.getDistance());
    SmartDashboard.putNumber("Rear Right Encoder", RearRightEncoder.getDistance());
    SmartDashboard.putNumber("Rear Left Encoder", RearLeftEncoder.getDistance());
    
    myDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
    myDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
  
    myDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, false);
    myDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, false);
    
}
   
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        while (isOperatorControl() && isEnabled()) {
             double angle = gyro.getAngle();
            myDrive.mecanumDrive_Cartesian(moveStick.getX(), moveStick.getY(),moveStick.getTwist(),0);
            Timer.delay(0.01);
            SmartDashboard.putNumber("angle", angle);
            double Armpossicion =  myArmpossicion.getVoltage();
            SmartDashboard.putNumber("Armpossicion",Armpossicion );
            Frontmotor2.set(ballcontrolStick.getTwist());
            Frontmotor1.set(ballcontrolStick.getTwist());
       
            if (Armpossicion<=1.25 && ballcontrolStick.getRawButton(1))
            {    
            Backmotor1.set(-1);
            Backmotor2.set(-1);
            }
            else if (Armpossicion<=1.25 && ballcontrolStick.getRawButton(4))
            {    
            Backmotor1.set(-.75);
            Backmotor2.set(-.75);
            }
            else if (Armpossicion<=1.25 && ballcontrolStick.getRawButton(3))
            {    
            Backmotor1.set(-.5);
            Backmotor2.set(-.5);
            }
            
            else if (Armpossicion>=0.29 && ballcontrolStick.getRawButton(2))
            {    
            Backmotor1.set(.2);
            Backmotor2.set(.2);
            }
            else
            {    
            Backmotor1.set(0);
            Backmotor2.set(0);
            }
                
            
                
                    }

    }
    
    /**
     * This function is called once each time the robot enters test mode.
     */ 
    public void test() {
    
    }
}