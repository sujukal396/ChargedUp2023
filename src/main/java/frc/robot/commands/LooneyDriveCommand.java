// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class LooneyDriveCommand extends CommandBase {
  /** Creates a new LooneyDriveCommand. */
  DrivetrainSubsystem drivetrain;
  DoubleSupplier speedSupplier;
  DoubleSupplier rotationSupplier;
  BooleanSupplier turnInPlace;
  BooleanSupplier fast;
  BooleanSupplier reverse;
  
  public LooneyDriveCommand(DrivetrainSubsystem drivetrain, DoubleSupplier speedSupplier, DoubleSupplier rotationSupplier, BooleanSupplier turnInPlace, BooleanSupplier fast, BooleanSupplier reverse) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
    this.drivetrain = drivetrain;
    this.speedSupplier = speedSupplier;
    this.rotationSupplier = rotationSupplier;
    this.turnInPlace = turnInPlace;
    this.fast = fast;
    this.reverse = reverse;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speedMulti = .66;
    double rotationMulti = .66;
    
    if (fast.getAsBoolean()) {
      speedMulti = .45;
      rotationMulti = .45;
    }
    if (reverse.getAsBoolean()){
      speedMulti= speedMulti * -1.0;
    }
    if (turnInPlace.getAsBoolean()){
      rotationMulti = rotationMulti * 0.5;
    }

    drivetrain.curvatureDrive(speedSupplier.getAsDouble()*speedMulti, rotationSupplier.getAsDouble()*rotationMulti, turnInPlace.getAsBoolean());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
