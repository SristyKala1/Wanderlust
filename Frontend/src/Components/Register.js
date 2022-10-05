import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import axios from 'axios'
import { backendUrlRegister } from '../BackendURL';
import {Users} from './models/User';
class Register extends Component {
  constructor(props) {
    super(props);
    this.state = {
      formValue: {
        userName: "",
        emailId: "",
        contactNumber: "",
        password: ""
      },
      formErrorMessage: {
        userName: "",
        emailId: "",
        contactNumber: "",
        password: ""
      },
      formValid: {
        userName: false,
        emailId: false,
        contactNumber: false,
        password: false,
        buttonActive: false
      },
      errorMessage: "",
      successMessage: ""

    }
  }

  handleChange = (event) => {
    let { name, value } = event.target
    let { formValue } = this.state
    this.setState({ formValue: { ...formValue, [name]: value } })
    this.validateField(name, value);

  }

  validateField = (name, value) => {
    var { formErrorMessage, formValid } = this.state
    switch (name) {
      case "userName":
        if (value === "") {
          formErrorMessage.userName = "Field Required"
          formValid.userName = false
        }
        else if (!value.match(/^[A-Za-z]+([\s][A-Za-z]+)*$/)) {
          formErrorMessage.userName = "should contain only English Alphabets and Spaces.Should not start or end with space."
          formValid.userName = false
        }
        else {
          formErrorMessage.userName = ""
          formValid.userName = true
        }
        break;
      case "emailId":
        if (value === "") {
          formErrorMessage.emailId = "Field Required"
          formValid.emailId = false
        }
        else if (!value.match(/^[A-z0-9]+@[A-z0-9]+(\.com)$/)) {
          formErrorMessage.emailId = "Please enter a valid Email Id."
          formValid.emailId = false
        }
        else {
          formErrorMessage.emailId = ""
          formValid.emailId = true
        }
        break;
      case "contactNumber":
        if (value === "") {
          formErrorMessage.contactNumber = "Field Required"
          formValid.contactNumber = false
        }
        else if (!(value.match(/^[6789][0-9]{9}$/))) {
          formErrorMessage.contactNumber = "Please enter a valid Contact number"
          formValid.contactNumber = false
        }
        else {
          formErrorMessage.contactNumber = ""
          formValid.contactNumber = true
        }
        break;
      case "password":
        let errorMessage = "field required";
        if (value) {
          var regexCap = new RegExp(/^.*[A-Z].*$/);
          var regexLow = new RegExp(/^.*[a-z].*$/);
          var regexNum = new RegExp(/^.*[0-9].*$/);
          var regexCha = new RegExp(/^.*[!@#$%^&*].*$/);
          regexCap.test(value)
            ? (errorMessage = "")
            : (errorMessage =
              errorMessage + " Should contain atleast 1 upper case letter");
          regexLow.test(value)
            ? (errorMessage = "")
            : (errorMessage =
              errorMessage + " Should contain atleast 1 lower case letter ");
          regexNum.test(value)
            ? (errorMessage = "")
            : (errorMessage =
              errorMessage + " Should contain atleast 1 number");
          regexCha.test(value)
            ? (errorMessage = "")
            : (errorMessage =
              errorMessage + " Should contain atleast 1 special character");
          value.length >= 7 && value.length <= 20
            ? (errorMessage = "")
            : (errorMessage =
              errorMessage + " Should contain atleast min 7 and max 20 characters");
          if (errorMessage === "") {
            formErrorMessage.password = ""
            formValid.password = true
          } else {
            formErrorMessage.password = errorMessage
            formValid.password = false
          }
        }
        break;
      default:
        break;
    }

    formValid.buttonActive = formValid.userName && formValid.emailId && formValid.contactNumber && formValid.password
    this.setState({ formErrorMessage: formErrorMessage, formValid: formValid, successMessage: "", errorMessage: "" })
  }



  handleSubmit = (event) => {
    event.preventDefault()
    this.submitRegister()
  }

  submitRegister = () => {
    var { formValue } = this.state
    var successMessage = "Congratulations! You have successfully been registered to Wanderlust. Please log in to utilise most of our functionalities."
    console.table([formValue])

    var user = new Users();
    user.contactNumber=this.state.formValue.contactNumber;
    user.emailId=this.state.formValue.emailId;
    user.password=this.state.formValue.password;
    user.userName=this.state.formValue.userName;

    axios.post(backendUrlRegister, user)
      .then((response) => {
        console.table([response.data]);
        this.setState({ successMessage: successMessage, errorMessage: '' })
      }).catch((error) => {
        if (error.response) {
          this.setState({ errorMessage: error.response.data.message, successMessage: '' })
          console.table([error.response])
        } else
          this.setState({ errorMessage: error.message + " Server Down", successMessage: '' })
      })
    console.log("Form is submitted successfully");

  }

  cancel = () => {
    var contactNumber = null;
    var password = null;
    var userName = null;
    var emailId = null;
    window.location.reload();
  };

  render() {
    return (<React.Fragment>
      <div className="container-fluid px-0">
      <div class="registration-bg ">
        <div className="row">
          <div className=" col-lg-6 offset-3">
         <br/>
            <div className="card">
            <div className="card-header text-black">
            <center><h3>Connect with WanderLust</h3></center>
            </div>
            <div className="card-body">
            <span>
              {(this.state.successMessage !== "" ? <center><div><h3 className="text-success">SUCCESSFULLY REGISTERED!</h3></div></center> : "")}
            </span>
              <form onSubmit={this.handleSubmit} className="form-horizontal">

                <div className="form-group">
                  <label htmlFor="userName"className="offset-1 d-flex justify-content"><h6>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Name<sup className="text-danger">*</sup></h6></label>
                  <center><input id="userName"
               
                    type="text"
                    required
                   
                    name="userName"
                    onChange={this.handleChange}
                    className="form-control w-75"
                    placeholder="Enter Name" />
                    {this.state.formErrorMessage.userName ? (
                      <span className="text text-danger">{this.state.formErrorMessage.userName}</span>
                    ) : null}</center>

                </div>
                <div className="form-group">
                  <label htmlFor="emailId"className="offset-1 d-flex justify-content"><h6>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Email Id<sup className="text-danger">*</sup></h6></label>
                  <center><input id="emailId"
                    type="email"
                    required
                    
                    name="emailId"
                    onChange={this.handleChange}
                    className="form-control w-75"
                    placeholder="Enter Email Id" />
                    {this.state.formErrorMessage.emailId ? (
                      <span className="text text-danger">{this.state.formErrorMessage.emailId}</span>
                    ) : null}</center>

                </div>
                <div className="form-group">
                  <label htmlFor="contactNumber" className="offset-1 d-flex justify-content"><h6>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Contact Number<sup className="text-danger">*</sup></h6></label>
                  <center><input
                    id="contactNumber"
                    required
                    
                    type="number"
                    min="6000000000"
                    max="9999999999"
                    name="contactNumber"
                    onChange={this.handleChange}
                    className="form-control w-75"
                    placeholder="Enter Contact Number" />
                    {this.state.formErrorMessage.contactNumber ? (
                      <span className="text text-danger">{this.state.formErrorMessage.contactNumber}</span>
                    ) : null}</center>

                </div>
                
                <div className="form-group">
                  <label htmlFor="password" className="offset-1 d-flex justify-content"><h6>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Password<sup className="text-danger">*</sup></h6></label>
                  <center> <input id="password"
                    type="password"
                    required 
                    name="password"
                    onChange={this.handleChange}
                    className="form-control w-75"
                    placeholder="Enter Password" />
                    {this.state.formErrorMessage.password ? (
                      <span className="text text-danger">{this.state.formErrorMessage.password}</span>
                    ) : null} </center>

                </div>
                <div><sup className="text-danger">*</sup>Marked Fields Are Mandatory</div><br />
                <button className="btn btn-primary" disabled={!this.state.formValid.buttonActive} type="submit">Connect</button> &nbsp;
                <button type="submit" onClick={() => window.location.reload()} className="btn btn-secondary" name="cancel">Refresh</button>
                <br /><br />
                <center>  <span className="text-success text-center font-weight-bold" name="successMessage" >{this.state.successMessage}</span></center><br />
                {this.state.successMessage ? <b><Link to="/login">Click here to Login</Link></b> : null}
                {this.state.errorMessage ? (<div className="text-danger font-weight-bold">{this.state.errorMessage}</div>) : (null)}
              </form>
               </div>
              </div>
              
          </div>
        </div>
        </div>
      </div>
    </React.Fragment>
    )
  }
}

export default Register