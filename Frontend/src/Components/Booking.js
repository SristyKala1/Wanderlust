import React, { Component } from "react";
import axios from "axios";
import { backendUrldId, backendUrlCheckItinerary } from "../BackendURL";
import { backendUrlBooking } from "../BackendURL";
import { Users } from "./models/User";
import { Redirect ,Link} from "react-router-dom";
import Collapse from "react-bootstrap/Collapse"
import {backendUrlBooking_checkItinerary} from "../BackendURL";
import { Dialog } from 'primereact/dialog';
import {Button} from "primereact/button";

class Booking extends React.Component{
    
    constructor(props){
        super(props)
        this.state={
            formValue:{
                noOfPeople:"",
                checkIn:"",
                user:"",
                destination:"",
                totalCost:0,
                booking:""
            },
            formErrorMessages:{
                numberOfTravellers:"",
                tripStartDate:""

            },
            formValid:{
                numberOfTravellers:false,
                tripStartDate:false,
                buttonActive:false
            },
            
            errorMessage:"",
            successMessage:"",
            flightCharge:0,
            endDate:"",
            startDate:"",
            collapse1:false,
            collapse2:false,
            collapse3:false,
            toggle:false,
            showModel : false
            

        }

    }



    

    handler=(event)=>{
        this.setState({endDate:"",tc:""})// end date and total cost are to be calculated
        var name=event.target.name;
        var value=event.target.value;
       
        var tc=this.state.formValue.totalCost;
        if(name==="checkIn" && value!==""){
            var date1=new Date(value)
            
            var days=this.state.formValue.destination.noOfNights
            date1.setDate(date1.getDate()+days) //adding startdate+no.of nights
            const months=["January","February","March","April","May","June","July","August","September","October","Noverber","December"]
            var date2=new Date(value)
            var date2mod=date2.getMonth()
            var startDate=months[date2mod]+" "+date2.getDate()+","+date2.getFullYear() //start date
            var mdate=date1.getMonth()
            var modate=months[mdate]+" "+date1.getDate()+", "+date1.getFullYear() //end date
            this.setState({endDate:modate,startDate:startDate})


        }
        //checking min of 1 and max of 5 people are there & calculating cost
        if(name==="noOfPeople" && value>0 && value<6 && value!==""){
            
            tc=value*this.state.formValue.destination.chargePerPerson+(this.state.toggle?this.state.formValue.destination.flightCharge*value:0)  
           
        }
        var formValue=this.state.formValue
        this.setState({formValue:{...formValue,[name]:value,totalCost:tc}})
        this.validate(name,value)
    }
    validate=(name,value)=>{
        var formErrorMessages=this.state.formErrorMessages
        var formValid=this.state.formValid
        //checking the condition
        switch(name){
            case "noOfPeople":
                if(value===""){
                    formErrorMessages.numberOfTravellers="Field Required"
                    formValid.numberOfTravellers=false
                }
                else if(value<1||value>5){
                    formErrorMessages.numberOfTravellers="Number of Travellers Should be 1 to 5."
                    formValid.numberOfTravellers=false
                }
                else{
                    formErrorMessages.numberOfTravellers=""
                    formValid.numberOfTravellers=true
                }
                break;
                //validating the tour selected date
            case "checkIn":
                if(value===""){
                    formErrorMessages.tripStartDate="Field Required"
                    formValid.tripStartDate=false
                }
                
                else if(new Date(value)<=new Date()){
                    formErrorMessages.tripStartDate="Trip Start Date Should Be After Today's Date"
                    formValid.tripStartDate=false
                }
                else{
                    formErrorMessages.tripStartDate=""
                    formValid.tripStartDate=true
                }
                break;
               default: 
        }
        this.setState({errorMessage:"",successMessage:""})
        formValid.buttonActive=formValid.numberOfTravellers&&formValid.tripStartDate
        this.setState({formErrorMessages:formErrorMessages,formValid:formValid})

    }
    componentWillMount=()=>{
        var user = new Users();
        user.userId = sessionStorage.getItem("userId");
        user.userName = sessionStorage.getItem("userName");
        var formValue=this.state.formValue

        this.setState({formValue:{...formValue,user:user}})

    }

    componentDidMount=()=>{
        
        var backendUrl=backendUrlCheckItinerary+this.props.match.params.destinationId
        axios.get(backendUrl)
        .then(response=>{
            var formValue=this.state.formValue
            
            this.setState({formValue:{...formValue,destination:response.data}})

        })
        .catch(error=>{
            
            if(error.response){
                this.setState({errorMessage:error.response.data.message})
            }
            else{
                this.setState({errorMessage:"Sorry!Server Down"})
            }
        })
    }
    handle=(event)=>{
        var name=event.target.id
        name="."+name;
        
        if(document.querySelector(name).style.display==="flex"){
            return document.querySelector(name).style.display="none";
        }
        else{
            return document.querySelector(name).style.display="flex";
        }
        
    }
    handleToggle=(event)=>{
        var flightCharge=this.state.formValue.destination.flightCharge
        var formValue=this.state.formValue
        var tc=formValue.totalCost

        if(this.state.flightCharge===0){
            
            
            //calculating flight cost
            tc=tc+(flightCharge*formValue.noOfPeople)
            
            
            this.setState({flightCharge:flightCharge})
            this.setState({formValue:{...formValue,totalCost:tc},toggle:true})

        }
        else{
            tc=tc-(flightCharge*formValue.noOfPeople)
            this.setState({flightCharge:0})
            this.setState({formValue:{...formValue,totalCost:tc},toggle:false})
        }
        
    }
    handleSubmit=(event)=>{
        event.preventDefault();
        this.handleBooking()
    }
    handleBooking=()=>{
        this.setState({errorMessage:"",successMessage:""})
        var formValue=this.state.formValue
        formValue.totalCost = Math.round((this.state.formValue.totalCost-(this.state.formValue.totalCost*this.state.formValue.destination.discount/100))*100)/100
        //adding formvalue data to db
        var url = backendUrlBooking + this.state.formValue.user.userId +"/"+ this.state.formValue.destination.destinationId;
        console.log(url);
        console.table([formValue]);
        axios.post(url,this.state.formValue)
        .then(response=>{ 
            this.setState({formValue:{...formValue,booking:response.data}})
            this.setState({showModel : true})
        })
        .catch(error=>{
            console.log(error)
            if(error.response){
                this.setState({errorMessage:error.response.data.message})
            }
            else{
                this.setState({errorMessage:"sorry!server error"})
            }

        })
    }
    //to showing overview,itenary& package inclusion
    handleCollapse=(event)=>{
        
        switch(event.target.id){
            case "collapse1":
                if(this.state.collapse1===false){
                    this.setState({collapse1:true})
                    document.getElementById("collapse1").innerHTML="- OverView";

                }
                else{
                    this.setState({collapse1:false})
                    document.getElementById("collapse1").innerHTML="+ OverView";
                }
               
                
                break;
            case "collapse2":
                    if(this.state.collapse2===false){
                        this.setState({collapse2:true})
                        document.getElementById("collapse2").innerHTML="- Package Inclusions";
    
                    }
                    else{
                        this.setState({collapse2:false})
                        document.getElementById("collapse2").innerHTML="+ Package Inclusions";
                    }
                    break;
            case "collapse3":
                    if(this.state.collapse3===false){
                        this.setState({collapse3:true})
                        document.getElementById("collapse3").innerHTML="- Itinerary";
    
                    }
                    else{
                        this.setState({collapse3:false})
                        document.getElementById("collapse3").innerHTML="+ Itinerary";
                    }
                    break;
                    default:
        }
        
    }
    getItenary=(x)=>{
        //fetching daywise plan
        var a=[]
       for (var i in x.detailsDTO.itinerary.restOfDays.split(",")){
           var c=parseInt(i)+2;
       var b=<div><div><h4>Day{c}</h4></div><li>{x.detailsDTO.itinerary.restOfDays.split(",")[i]}</li></div>
       a.push(b)

                                
        }
        return a;
    }

    setShow = event => {
        this.setState({  showModel: true });
    
      };

    hideShow = () => {
        this.setState({ showModel:false});
      };

 
    render(){
        const footer = (
            <div>
                <Link to="/viewBookings">
                <Button label="View Booking" icon="pi pi-check" onClick={()=><Redirect to="/viewBooking" />} />
                </Link>

                <Link to="/">
                <Button label="Home" onClick={()=><Redirect to="/"/>} />
                </Link>
               
               
            </div>
        );
        if(sessionStorage.getItem("userId")==null){
            return (
                <div className="row bg-reg">
               
            <div className=" mx-auto">
              <div className="card-transparent card-mod" >
                
                 <div className="text-center">
                        <h4>
    
                         <span className="font-weight-bold  ">Please Login to see your bookings</span>
                           
                        </h4>
                 </div>
                 <div className="card-body">
                        <Link to="/login">
                        <button type="button" style={{float:"left"}} className="btn btn-success " onClick={()=><Redirect to="/login"/>}>Login</button>
                        </Link>
                        <button type="button" style={{float:"right"}} className="btn btn-danger " onClick={()=>window.history.back()}>Go Back</button>
                        </div>
              </div>              

</div>
</div> )    
        }

       
          //on success booking
        if(this.state.formValue.booking!==""){
            
            return(
           
          <div className="bg-modal">
          
          <Dialog header="Itinerary" visible={this.state.showModel} model={true} maximizable={true}
              contentStyle={{ maxHeight: "500px", overflow: "auto" }}
              contentClassName="p-dialog p-dialog-title"
              position="center"
              blockScroll={true}
              footer = {footer}
            >
            <div className="modal-content">
                <div className="content-1">
                    <h1>Booking Confirmed</h1>
                    <h3 className="text-success">Congratulations! trip planned to  {this.state.formValue.destination.destinationName}</h3>
                   <h4>Trip starts on: {this.state.startDate}</h4>
                   <h4> Trip ends on: {this.state.endDate}</h4>
                   

                </div>
                

            </div>
</Dialog>
       </div>)
       
            
   }
   else if(this.state.formValue.destination){


   var destination=this.state.formValue.destination
   
   
  
   
   return(<React.Fragment>
       
       
      
       <div className="row">
          
       
           <div classsName="htmlall ">
          
           <h1>{this.state.formValue.destination.destinationName} </h1>

           <div className="card offset-1 card-m card-header bg-info">
           <div  className="htmla" id="collapse1" onClick={this.handleCollapse} >+ OverView
                    </div> </div>
                    <div className="card offset-1 card-m card-body">
                    <Collapse in={this.state.collapse1}>
                        <div className="coll-mod">{destination.detailsDTO.about}</div>
                    </Collapse> </div> 

                    <div className="card offset-1 card-m card-header bg-warning">
                <div  className="htmla" id="collapse2" onClick={this.handleCollapse}>+ Package Details
                    </div>  </div>
                    <div className="card offset-1 card-m card-body">
                    <Collapse in={this.state.collapse2}>
                        <div className="coll-mod"><ul>{destination.detailsDTO.packageInclusion.split(",").map(x => {
                        return <li>{x}</li>})}</ul></div>
                    </Collapse>  </div>

                    <div className="card offset-1 card-m card-header bg-info">
                <div  className="htmla" id="collapse3" onClick={this.handleCollapse}>+ Itinenary
                    </div>  </div>
                    <div className="card offset-1 card-m card-body">
                    <Collapse in={this.state.collapse3}>
                        <div className="coll-mod">
                            <h4>Day Wise Itinerary</h4>
                            <ul>
                            <div><h5>Day1</h5></div>
                            <li>{destination.detailsDTO.itinerary.firstDay}</li>
                            {this.getItenary(destination)}
 
                            <div><h5>Last Day</h5></div>
                            <li>{destination.detailsDTO.itinerary.lastDay}</li>
                            </ul></div>
                    </Collapse>  </div>
                 </div>
                    <div className="card offset-1 card-m">
               
               <div className="card-header bg-info">
                   <h3 className="text-center">BOOK HERE</h3>
                      <h4 className="text-center text-warning">Come let's Wander</h4>

               </div>
               <div className="card-body">
                   <form onSubmit={this.handleSubmit}>
                   <div className="form-group">
                       <label className="bold d-flex justify-content" >Number Of Travellers</label>
                       <input type="number" name="noOfPeople" onChange={this.handler} placeholder="Enter No of Travelleres" className="form-control"></input>
                       <span name="noOfTravellersError" className="text-danger">{this.state.formErrorMessages.numberOfTravellers}</span>
                   </div>
                   <div className="form-group">
                       <label className="bold d-flex justify-content" >Trip Start Date</label>
                       <input type="date" name="checkIn"  onChange={this.handler} className="form-control"></input>
                       <span name="tripStartDateError" className="text-danger">{this.state.formErrorMessages.tripStartDate}</span>
                   </div>
                   <span className="bold d-flex justify-content">Include Flight  <label  className="switch" >
                               <input name="checkbox" type="checkbox" className="toggle-switch-checkbox" onClick={this.handleToggle}></input>
                               <span  className="slider round" ></span>
                           </label></span><br/>
                 
                      
                  
                       <span className="bold d-flex justify-content">{(this.state.formValue.checkIn?<span>Your Trip Ends On: {this.state.endDate}</span>:"")}</span><br/>
                   <span className="bold d-flex justify-content">{(this.state.formValue.noOfPeople>0&&this.state.formValue.noOfPeople<6?<span>You have to pay: ${Math.round((this.state.formValue.totalCost-(this.state.formValue.totalCost*this.state.formValue.destination.discount/100))*100)/100}</span>:"")}</span>
                   
                   <div className="row">
                       
                           <button type="submit" className="btn btn-info adj" name="confirmBook" onClick={this.handleSubmit} disabled={!this.state.formValid.buttonActive}>Confirm Booking</button>
                         
                           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                           <button type="button" className="btn btn-danger adj2" name="goBack" onClick={()=>{window.history.back()}}>Go Back</button>


                   </div>
                   
                       
               </form>

               </div>
               
           </div>

                    </div>
            </React.Fragment>

        )
    }
    else{
        return(null)
    }
}
}

export default Booking;