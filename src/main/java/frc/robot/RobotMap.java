/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANDigitalInput;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Compressor;

import edu.wpi.first.wpilibj.DigitalInput;
import com.revrobotics.CANDigitalInput.LimitSwitch;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;


  //Chasis puertos
  
  
  public static int id_m_chasis_fl =3;
  public static int id_m_chasis_fr =1;
  public static int id_m_chasis_bl =4;
  public static int id_m_chasis_br =2;
  
  

  //Intake de Discos puertos
  /*
  public static int id_m_intake_L=2;
  public static int id_m_intake_R=1;
  
  
  public static CANDigitalInput ls_intake_discos;
  */
  //climb puertos

  public static int id_climb_for=0;
  public static int id_climb_rev=1;

  public static Compressor compresor;

  /////////////////////////////////////////////////////////////////////////////////////////////
  
  //Chasis Neos
  /*
  public static CANSparkMax csm_m_chasis_fl = new CANSparkMax(id_m_chasis_fl, MotorType.kBrushless);
  public static CANSparkMax csm_m_chasis_fr = new CANSparkMax(id_m_chasis_fr, MotorType.kBrushless);
  public static CANSparkMax csm_m_chasis_bl = new CANSparkMax(id_m_chasis_bl, MotorType.kBrushless);
  public static CANSparkMax csm_m_chasis_br = new CANSparkMax(id_m_chasis_br, MotorType.kBrushless);
  */


  //Chasis Sims
  public static TalonSRX M_Chasis_fr = new TalonSRX(id_m_chasis_fr);
  public static TalonSRX M_Chasis_fl = new TalonSRX(id_m_chasis_fl);
  public static TalonSRX M_Chasis_br = new TalonSRX(id_m_chasis_br);
  public static TalonSRX M_Chasis_bl = new TalonSRX(id_m_chasis_bl);
  

  //Intake Discos

  /*
  public static TalonSRX M_intake_L = new TalonSRX(id_m_intake_L);
  public static TalonSRX M_intake_R = new TalonSRX(id_m_intake_R);
  */
  
  //Climb
  
  public static DoubleSolenoid ds_climb_piston;
  

///////////////////////////////////////////////////////////////////////////////////////////////////
  public static void init(){

    //Climb//
    ds_climb_piston = new DoubleSolenoid(id_climb_for, id_climb_rev);
    compresor = new Compressor(0);
    compresor.setClosedLoopControl(true);
    

    //Chasis  Neos

    /*
    csm_m_chasis_fl.set(0);
    csm_m_chasis_fl.setIdleMode(CANSparkMax.IdleMode.kBrake);
    
    csm_m_chasis_fr.set(0);
    csm_m_chasis_fr.setIdleMode(CANSparkMax.IdleMode.kBrake);
    csm_m_chasis_fr.setInverted(true);

    csm_m_chasis_bl.set(0);
    csm_m_chasis_bl.setIdleMode(CANSparkMax.IdleMode.kBrake);
    csm_m_chasis_bl.follow(csm_m_chasis_fl);

    csm_m_chasis_br.set(0);
    csm_m_chasis_br.setIdleMode(CANSparkMax.IdleMode.kBrake);
    csm_m_chasis_br.setInverted(true);
    csm_m_chasis_bl.follow(csm_m_chasis_fr);
    */

    //Chasis Sims

    
    M_Chasis_fl.set(ControlMode.PercentOutput, 0);
    M_Chasis_fl.setNeutralMode(NeutralMode.Brake);

    M_Chasis_bl.set(ControlMode.PercentOutput, 0);
    M_Chasis_bl.setNeutralMode(NeutralMode.Brake);
    M_Chasis_bl.follow(M_Chasis_fl);

    M_Chasis_fr.set(ControlMode.PercentOutput, 0);
    M_Chasis_fr.setNeutralMode(NeutralMode.Brake);
    M_Chasis_fr.setInverted(true);

    M_Chasis_br.set(ControlMode.PercentOutput, 0);
    M_Chasis_br.setNeutralMode(NeutralMode.Brake);
    M_Chasis_br.follow(M_Chasis_fr);
    M_Chasis_br.setInverted(true);
    
    

    //Intake//
    /*
    M_intake_R.overrideLimitSwitchesEnable(true);
    //M_intake_R.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 7);
    //M_intake_L.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 7);
    //M_intake_R.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 7);
    // M_intake_R.set(ControlMode.PercentOutput, 0);
    M_intake_R.setNeutralMode(NeutralMode.Brake);
    //M_intake_R.overrideLimitSwitchesEnable(true);
    M_intake_R.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed,0);
    //M_intake_R.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen,0);
    
    M_intake_L.set(ControlMode.PercentOutput,0);
    M_intake_L.setNeutralMode(NeutralMode.Brake);
    M_intake_L.setInverted(true);
    M_intake_L.follow(M_intake_R);
    */
  
    
    

  }


}
