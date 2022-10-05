import React, { Component } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import { backendUrlListOfBooking, backendUrlSearchReview, backendUrlAddReview } from "../BackendURL";
import { backendUrlDeleteBooking } from "../BackendURL";
import { Redirect } from "react-router-dom";
import { Dialog } from "primereact/dialog"
import { Rating } from 'primereact/rating';
//import {  backendUrlUserBooking} from '../BackendURL';

import "../custom.css";

import { Users } from './models/User';
import { Booking } from './models/Booking';


class PlannedTrips extends Component {
    
    constructor(props) {
        super(props);
        var date = new Date();
        var date1 = date.getFullYear() + '-' + (date.getMonth()+1) + '-' + date.getDate()
       
        this.state = {
            visible: "",
            bookingId: "",
            checkIn: "",
            checkOut: "",
            noOfPeople: "",
            PlannedTrips: [],
            Review : [],
            successMessage: "",
            errorMessage: "",
            cancelErrorMessage: "",
            refund: 0,
            url: backendUrlListOfBooking + sessionStorage.getItem("userId"),
            url2: backendUrlDeleteBooking,
            showModel: false,
            showModelAddReview : false,
            alertOpenSuccess : false,
            alertOpenError : false,
            today : new Date(date1),
            formRatingValue : {
                rating : 0,
                review : "",
                url : "",
                booking :""
            }

        }
      
    }
    onClick = (event) => {

        this.setState({ visible: event.target.value,
        showModel : true });
    }
    onHide = () => {
        this.setState({ visible: "",
        showModel: false });
    }

    componentDidMount = () => {
        axios.get(this.state.url)
            .then(response => {
                this.setState({ PlannedTrips: response.data })

            })
            .catch(error => {
                if (error.response) {
                    this.setState({ errorMessage: error.response.data.message,showModel : false })
                }
                else {
                    this.setState({ cancelErrorMessage: "Server down" ,alertOpenError : true ,showModel : false})
                }

            })

            axios.get(backendUrlSearchReview + sessionStorage.getItem("userId"))
            .then(response => {
                this.setState({ Review: response.data })

            })
            .catch(error => {
                console.log(error)
                if (error.response) {
                   
                }
                else {
                   
                }

            })

    }
    handleCancel = (event) => {
        this.setState({ visible: "", errorMessage: "" });
        var a = this.state.url2 + event.target.value
        console.log(a)
        axios.delete(a) //deleting data from database
            .then(response => {
                //document.getElementById("modal").style.display = "block"
                this.setState({ showModel :false,alertOpenSuccess : true})
                this.componentDidMount()
            })
            .catch(error => {

                
                if (error.response) {
                    this.setState({ cancelErrorMessage : "Cancellation Failed!! " +  error.response.data.message ,alertOpenError : true,showModel : false})
                }
                else {
                    this.setState({ cancelErrorMessage: "Server down", alertOpenError : true,showModel : false })
                }
            })

    }
    onFormValueChange = (e) =>{
       var  formRating = this.state.formRatingValue
        this.setState({ formRatingValue: { ...formRating,[e.target.name] : e.target.value } })
    }

    handleSubmit = (event) =>{
        event.preventDefault()
        this.submitRegistration(event.target.value)
    }

    submitRegistration = (bookingId) =>{
        var booking = new Booking();
        booking.bookingId = bookingId
        var  formRating = this.state.formRatingValue
        this.setState({ formRatingValue: { ...formRating, booking :booking  } },() =>{
            axios.post(backendUrlAddReview,this.state.formRatingValue).
            then( res => {
                this.setState({ successMessage: "successfully review" , showModelAddReview : false })
                this.componentDidMount()
            })
            .catch(err =>{
                if (err.response) {
                    this.setState({ showModelAddReview : false })
                    this.componentDidMount()
                    
                }
                else {
                    this.setState({ showModelAddReview : false })
                    this.componentDidMount()
                   // this.setState({ errorMessage: "Server Down" })
                }
            })
        })
       
    }

    render() {

      
        if (sessionStorage.getItem("userId") == null) {
            return <div className="row bg-reg">


                <div className=" mx-auto">
                    <div className="card-transparent  card-mod" >
                        <br/> <br/>
                        <div className="text-center">
                            <h4>

                                <span className="font-weight-bold  ">Please Login to see your bookings</span>

                            </h4>
                        </div>
                        <br/> 
                        <div className="card-body">
                            <Link to="/login">
                                <button type="button" style={{ float: "left" }} className="btn btn-outline-success " onClick={() => <Redirect to="/login" />}>Login</button>
                            </Link>
                            <button type="button" style={{ float: "right" }} className="btn btn-outline-danger " onClick={() => window.history.back()}>Go Back</button>
                        </div>
                    </div>

                </div>
            </div>

        }

        return (
            
            <div >
                <React.Fragment>

                    
                       {/*confirm cancellation*/}
                    {!this.state.errorMessage ?
                        <div>
                                <Dialog header="Cancellation" visible={this.state.showModel} model={true}
              contentStyle={{ maxHeight: "500px", overflow: "auto",height : "100" }}
              contentClassName="p-dialog p-dialog-title"
              onHide={this.onHide}
              anchorOrigin={{vertical: 'top', horizontal: 'center'}}
              blockScroll={true}
            >
                            <div>{this.state.PlannedTrips.map(x => {
                      

                                if (x.bookingId == this.state.visible) {

                                    return <div className="bg-modal3">
                                        <div className="modal-content3" >
                                            <div className="card">
                                                <div className="card-header">
                                    
                                                    <h4>Confirm Cancellation</h4>

                                                </div>
                                                <div className="card-body">
                                                    <div>Trip start date: {x.checkIn}</div>
                                                    <div> Trip end date: {x.checkOut}</div>
                                                    <div>Refund Amount: ${x.totalCost}</div>

                                                </div>
                                                <div className="card-footer">
                                                    <div className="row">
                                                        <button className="btn btn-danger btn-adj3" value={x.bookingId} onClick={this.handleCancel}>Confirm Cancellation</button>
                                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    
                                        <button className="btn btn-info btn-adj2 " onClick={this.onHide}>Back</button>


                                                    </div>

                                                </div>

                                            </div>

                                        </div>

                                    </div>

                                }




                            })}</div>
                            </Dialog>


                           
                            <br /> <br /> <br />
                            {/*showing planned trips*/}
                            <div> {this.state.PlannedTrips.map(x => {
                                var ratingBoolean = false
                                                                     console.log(this.state.Review)
                                return <div className="container" className="container-padding">
                                    <div className="row ">
                                        <div className="col-6 offset-3">

                                            <div className="card">
                                                <div className="card-header bg-dark text-light">
                                                    <h4 className="text-italic">Booking ID : {x.bookingId}</h4>
                                                </div>
                                                <div className="card-body cd">

                                                    <div className="row">
                                                        <div className="col-6">
                                                            <b>Trip starts on : </b>{x.checkIn}{}<br />
                                                            <b>Trip ends on : </b>{x.checkOut}<br />
                                                            <b>Travellers count: </b>{x.noOfPeople}<br />

                                                        </div>
                                                        <div className="col-4 offset-1">
                                                            <b>Fare Details</b><br />
                                                            ${x.totalCost}<br/> 
                                                           {console.log(this.state.Review)}
                                                            { 
                                                                
                                                            this.state.today < new Date(x.checkIn) ?
                                                                
                                                                <button className="btn btn-link btn-adj5 btn-primary text-light" value={x.bookingId} onClick={this.onClick}>Cancellation&Claim Refund {ratingBoolean = true}</button>
                                                                : this.state.today < new Date(x.checkOut) ?
                            
                                                                    <b>You are in trip {ratingBoolean = true} </b>:
                                                                     (this.state.Review === "") ?
                                                                     <button className="btn btn-link btn-adj5 btn-primary text-light"  onClick={() => {this.setState({showModelAddReview : true})}}>Review Your Trip{ratingBoolean = true}</button>:
                                                                     this.state.Review.map( r =>{
                                                                        if(r.booking.bookingId === x.bookingId){
                                                                            ratingBoolean= true
                                                                            return(<Rating value={r.rating} readonly stars={5} cancel={false} />)
                                                                          
                                                                        }
                                                                     })
       
                                                            }
                                                            {
                                                                !ratingBoolean ? <div> <button className="btn btn-link btn-adj5 btn-primary text-light"  onClick={() => {this.setState({showModelAddReview : true})}}>Review Your Trip</button>
                                                                
																				<Dialog header="Review" visible={this.state.showModelAddReview} model={true}
                                                                                contentStyle={{ maxHeight: "500px", overflow: "auto",height : "100" }}
                                                                                contentClassName="p-dialog p-dialog-title"
                                                                                onHide={() => {this.setState({showModelAddReview:false})}}
                                                                                anchorOrigin={{vertical: 'top', horizontal: 'center'}}
                                                                                blockScroll={true}
																				>
																					<form>
																						<div class="form-group">
																						<label >Rating</label>
																						<Rating value={this.state.formRatingValue.rating}  name = "rating" stars={5} cancel={false}  onChange={this.onFormValueChange}/>
																						</div>
																						<div class="form-group">
																						<label>Review:</label>
																						<textarea class="form-control" value = {this.state.formRatingValue.review} name = "review" rows="5" id="comment" maxLength = "100"  onChange={this.onFormValueChange} ></textarea>
																						</div>
																						<button type="submit" class="btn btn-primary" value= {x.bookingId} onClick = {this.handleSubmit}>Submit</button>
																						
																					  
																					</form>
                                                                              </Dialog> 
                                                                </div>:null
                                                            }

                                                        </div>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <br /><br />
                                </div>
                            })}</div></div>
                        :
                        <div >
                            <div className="text-danger div-adj offset-5">{this.state.errorMessage}</div>
                            <br/>
                            <Link to="/packages">
                                <button className="div-adj2 btn btn-outline-success offset-5" onClick={() => <Redirect to="/packages" />}>Click Here To BOOK</button>
                            </Link>

                        </div>}

                </React.Fragment>
            </div>

        )
    }
}

export default PlannedTrips;