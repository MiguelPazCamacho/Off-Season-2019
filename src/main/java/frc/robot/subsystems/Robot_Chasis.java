/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Robot_Chasis extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public double Rotacion;
  public double Adelante;
  public double Atras;
  public double Lado_R;
  public double Lado_L; 
  public double velocidad;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void Stop_Chasis(){
    
    RobotMap.M_Chasis_fl.set(ControlMode.PercentOutput, 0); 
    RobotMap.M_Chasis_fr.set(ControlMode.PercentOutput, 0);
    

  }

  public void Move_Chasis(){
    Rotacion= Robot.m_oi.Stick_C.getRawAxis(0);
    Adelante= Robot.m_oi.Stick_C.getRawAxis(3);
    Atras=  -Robot.m_oi.Stick_C.getRawAxis(2);

    if (Adelante>0.15||Atras<-0.15){
      velocidad = Adelante+Atras;
      Lado_R =velocidad;
      Lado_L = velocidad;
      if(Rotacion<-0.20 || Rotacion>0.20){
        Lado_R = -velocidad*Rotacion + velocidad;
        Lado_L = velocidad*Rotacion + velocidad;
      }
      }else if(Rotacion>0.20||Rotacion<-0.20){
        Lado_R = Rotacion;
        Lado_L = -Rotacion;
      }else{
        Lado_L=0;
        Lado_R=0;
      }

      if (Lado_R>1) {
        Lado_R= 1;
      }else if (Lado_R<-1){
        Lado_R=-1;
      }

      if (Lado_L>1){
        Lado_L=1;
      }else if(Lado_L<-1){
        Lado_L= -1;
      }
      
      RobotMap.M_Chasis_fl.set(ControlMode.PercentOutput, Lado_L);
      RobotMap.M_Chasis_fr.set(ControlMode.PercentOutput, Lado_R);
      
  

    



    
  }
}
