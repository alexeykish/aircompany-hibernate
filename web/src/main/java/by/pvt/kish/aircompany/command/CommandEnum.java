package by.pvt.kish.aircompany.command;

import by.pvt.kish.aircompany.command.airport.AddAirportCommand;
import by.pvt.kish.aircompany.command.airport.DeleteAirportCommand;
import by.pvt.kish.aircompany.command.airport.GetAllAirportsCommand;
import by.pvt.kish.aircompany.command.airport.UpdateAirportCommand;
import by.pvt.kish.aircompany.command.employee.*;
import by.pvt.kish.aircompany.command.flight.*;
import by.pvt.kish.aircompany.command.plane.*;
import by.pvt.kish.aircompany.command.user.GetAllUsersCommand;
import by.pvt.kish.aircompany.command.user.LoginUserCommand;
import by.pvt.kish.aircompany.command.user.LogoutUserCommand;
import by.pvt.kish.aircompany.command.user.RegisterUserCommand;

/**
 * @author Kish Alexey
 */
public enum CommandEnum {
    ADD_FLIGHT_COMMAND 					{   {this.command = new AddFlightCommand();		    	}	},
    GET_ALL_FLIGHTS_COMMAND 		 	{	{this.command = new GetAllFlightsCommand();		    }	},
    DELETE_FLIGHT_COMMAND 			 	{	{this.command = new DeleteFlightCommand();	    	}	},
    UPDATE_FLIGHT_COMMAND   		    {	{this.command = new UpdateFlightCommand();	    	}	},
    FLIGHT_REPORT_COMMAND            	{	{this.command = new FlightReportCommand();	        }	},

    ADD_EMPLOYEE_COMMAND 			 	{   {this.command = new AddEmployeeCommand();		    }	},
    GET_ALL_EMPLOYEES_COMMAND 	    	{	{this.command = new GetAllEmployeesCommand();   	}	},
    DELETE_EMPLOYEE_COMMAND			 	{	{this.command = new DeleteEmployeeCommand();	    }	},
    UPDATE_EMPLOYEE_COMMAND            	{	{this.command = new UpdateEmployeeCommand();	    }	},
    EMPLOYEE_REPORT_COMMAND            	{	{this.command = new EmployeeReportCommand();	    }	},
    SET_EMPLOYEE_STATUS_COMMAND        	{	{this.command = new SetEmployeeStatusCommand();     }	},

    REGISTER_USER_COMMAND 			 	{   {this.command = new RegisterUserCommand();	    	}	},
    LOGIN_USER_COMMAND 				 	{   {this.command = new LoginUserCommand();		    	}	},
    LOGOUT_USER_COMMAND					{	{this.command = new LogoutUserCommand();	    	}	},
    GET_ALL_USERS_COMMAND				{	{this.command = new GetAllUsersCommand();	    	}	},

    ADD_PLANE_COMMAND 			    	{   {this.command = new AddPlaneCommand();		        }	},
    GET_ALL_PLANES_COMMAND 	    	    {	{this.command = new GetAllPlanesCommand();   	    }	},
    DELETE_PLANE_COMMAND			 	{	{this.command = new DeletePlaneCommand();	        }	},
    UPDATE_PLANE_COMMAND            	{	{this.command = new UpdatePlaneCommand();	        }	},
    PLANE_REPORT_COMMAND            	{	{this.command = new PlaneReportCommand();	        }	},
    SET_PLANE_STATUS_COMMAND           	{	{this.command = new SetPlaneStatusCommand();        }	},

    ADD_AIRPORT_COMMAND 			 	{   {this.command = new AddAirportCommand();		    }	},
    GET_ALL_AIRPORTS_COMMAND 	    	{	{this.command = new GetAllAirportsCommand();       	}	},
    DELETE_AIRPORT_COMMAND			 	{	{this.command = new DeleteAirportCommand();	        }	},
    UPDATE_AIRPORT_COMMAND            	{	{this.command = new UpdateAirportCommand();	        }	},

    SET_TEAM_COMMAND					{	{this.command = new SetTeamCommand();           	}	},
    SAVE_TEAM_TO_FLIGHT_COMMAND	    	{	{this.command = new SaveTeamToFlightCommand();      }	};

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
