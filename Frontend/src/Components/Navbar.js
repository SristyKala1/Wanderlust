import React, { Component } from "react";
import "../index.css";
import Home from "./Home";
import Login from "./Login";
import Packages from "./Packages";
import PlannedTrips from "./ViewBooking";
import { BrowserRouter as Router, Route, Switch, Link } from "react-router-dom";
import Register from "./Register";
import Search from "./Search";
import Booking from "./Booking";
import CarouselTour from "./CarouselTour";
class NavBar extends Component {
  constructor(props) {
    super(props);
    this.state = {
      logged_userId: sessionStorage.getItem("userId"),
      logged_userName: sessionStorage.getItem("userName"),
      dialog_visible: false,
      logged_out: false
    };
  }

  onClick = () => {
    this.setState({ dialog_visible: true });
  };

  onHide = () => {
    this.setState({ dialog_visible: false });
  };

  logout = () => {
    this.setState({ dialog_visible: false });
    sessionStorage.clear();
    this.setState({ logged_out: true });
    window.location.reload();
  };

  confirm_logout = () => {
    this.setState({ dialog_visible: true });
  };
  render() {
    return (
      <div>
        <Router>
          {/* <div className="container-fluid"> */}
          <div className="App">
            <nav className="navbar navbar-expand-md bg-dark navbar-dark">
              <div className="navbar-header">
                <Link className="navbar-brand" to="/">
                  Start Wandering
                </Link>
              </div>
              <ul className="navbar-nav ml-auto">
                {this.state.logged_userId ? (
                  <li className="nav-item">
                    <Link className="nav-link" to="">
                      Welcome {this.state.logged_userName}
                    </Link>
                  </li>
                ) : null}
                <li className="nav-item">
                  <Link className="nav-link" to="/quotes">
                    Travelling {" "}
                  </Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/packages">
                    Hot Deals{" "}
                  </Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/viewBookings">
                    Planned Trips
                  </Link>
                </li>

                {!this.state.logged_userId ? (
                  <li className="nav-item">
                    <Link className="nav-link" to="/login">
                      {" "}
                      Login
                    </Link>
                  </li>
                ) : null}
                {this.state.logged_userId ? (
                  <li className="nav-item">
                    <Link className="nav-link" onClick={this.logout} to="">
                      {" "}
                      Logout
                    </Link>
                  </li>
                ) : null}
              </ul>
            </nav>

            <Switch>
              <Route exact path="/" component={Home}></Route>
              <Route exact path="/login" component={Login}></Route>
              <Route path="/packages/:continent" component={Search}></Route> 
              
              <Route path ="/packages" component={Packages}></Route>            
              <Route exact path="/register" component={Register}></Route>
              <Route exact path="/booking/:destinationId" component={Booking}></Route>
              <Route exact path="/viewBookings" component={PlannedTrips}></Route>
              <Route path="/quotes" component={CarouselTour}></Route>
            </Switch>
          </div>
        </Router>
      </div>
    );
  }
}
export default NavBar;
