package org.usfirst.frc.team948.robot.commands;

import org.usfirst.frc.team948.robot.OI;
import org.usfirst.frc.team948.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MatchDesiredHeading extends Command implements InSub {
	
//	double targetHeading = (double) IO.dashboard.getValue("DesiredHeading", navx.getAngle());
	double targetHeading = -180;
	private double Turn_T = rm.preferences.getDouble("Turn_T", 1.0);
    public MatchDesiredHeading() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	drive.initTurnToHeading(targetHeading, Turn_T);
    }

    // Called repeatedly when this Command is scheduled to run
	protected void execute() {
//		targetHeading = (double) IO.dashboard.getValue("DesiredHeading", navx.getAngle());
    	drive.updateTurnToHeading(targetHeading);
    	drive.turnTurnToHeading();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	drive.endTurnToHeading();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
