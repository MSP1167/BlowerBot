/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.util.Xbox;
import frc.robot.commands.ManualCommandDrive;

/**
 * Add your docs here.
 */
public class SubsystemDrive extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private TalonSRX
    left,
    right;


  public SubsystemDrive() {
    left = new TalonSRX(Constants.LEFT_DRIVE_ID);
    right = new TalonSRX(Constants.RIGHT_DRIVE_ID);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new ManualCommandDrive());
  }

  public void Drive(Joystick joy) {
    double throttle = Xbox.RT(joy) - Xbox.LT(joy);
    double steering = Xbox.LEFT_X(joy);

    double driveRight = throttle + steering;
    double driveLeft = throttle - steering;

    driveRight = (driveRight < 0 ? 0 : (driveRight > 1 ? 1 : driveRight));
    driveLeft = (driveLeft < 0 ? 0 : (driveLeft > 1 ? 1 : driveLeft));

    right.set(ControlMode.PercentOutput, driveRight);
    left.set(ControlMode.PercentOutput, driveLeft);
  }
}
