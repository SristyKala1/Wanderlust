import React, { Component } from "react";
import axios from "axios";
import { backendUrlPackages, backendUrlViewReview } from "../BackendURL";
import { Link, Redirect } from "react-router-dom";
import { Dialog } from "primereact/dialog"
import { Rating } from 'primereact/rating';

class Search extends Component {
  constructor() {
    super()
    this.state = {
      packageDetails: [],
      errorMessage: "",
      successMessage: "",
      priceChange: false,
      nightChange: false,
      showModel: false,
      itinerary: "",
      reviewShowModel : false,
      reviewDestination : "",
      Review : []
    }
  }
  setShow = (event) => {
    this.setState({ itenary: event.target.value ,showModel :true})
   
  }
  hideShow = () => {
    this.setState({ showModel: false, itenary: "" });
  };
  reviewSetShow = (destination) =>{

    this.setState({ reviewDestination: destination, reviewShowModel: true });

  }

  reviewHideShow = () =>{
    this.setState({ reviewDestination: "", reviewShowModel: false });
  }

  componentDidMount = () => {
    this.setState({ errorMessage: "", successMessage: "" })
    var backendUrlPackageDetails = backendUrlPackages + this.props.match.params.continent;
    console.log(backendUrlPackageDetails + " url");
    axios.get(backendUrlPackageDetails)
      .then(response => {
        this.setState({ packageDetails: response.data })

      })
      .catch(error => {
        console.log(error + "in error")
        if (error.response) {
          this.setState({ errorMessage: error.response.data.message })
        }
        else {
          this.setState({ errorMessage: "Server Down" })
        }
      })

      axios
      .get(backendUrlViewReview)
      .then(response =>{
        console.log(response.data)
        this.setState({ Review: response.data },() =>{
        });
      }).catch(error =>{
        if (error.response) {
          this.setState({ errorMessage: error.response.data.message });
        } else {
          this.setState({ errorMessage: "Server Down" });
        }
      })
      console.log(this.state.Review)
  }

  view = (x) => <div className="card "><br /><br /><div className="row">

    <img src={x.imageUrl} alt=""  className="col-lg-3 offset-1"></img>
    <div className="col-lg-3 offset-1">

      <span className="night">{x.noOfNights} Number of Nights</span>
      {x.discount > 0 ? <span className="text-danger"><br></br>{x.discount}% Instant Discount<br /></span> : <br />}

      {x.about}
    </div>
    <div className="col-lg-3 ">

      <h4>Prices Starting From:</h4>
      <h4 className="text-success ">{new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(x.chargePerPerson)}</h4><br></br>
      <div className="row">
        <button className="btn btn-info btn-1" value={x.destinationId} onClick={this.setShow}>VIEW DETAILS</button> <br /><br />&nbsp;&nbsp;
                    <Link to={"/booking/" + x.destinationId}>
          <button className="btn btn-info  btn-1" onClick={() => <Redirect to={"/booking/" + x.destinationId} />}>BOOKING</button>
        </Link>
        
{console.log(x.rating+ ""+x.noOfRating)}
                  <Rating value={x.rating}  readonly stars={5} cancel={false}/>
                  <Link  onClick ={() => this.reviewSetShow(x)}>{x.noOfRating}</Link>

      </div>
    </div>
    <br />
  </div>
  </div>



  render() {
    var noReview = true
    return (
      <div>

<Dialog header="Review" visible={this.state.reviewShowModel} model={true} maximizable={true}
              contentStyle={{ maxHeight: "500px", overflow: "auto" }}
              contentClassName="p-dialog p-dialog-title"
              onHide={this.reviewHideShow}
              position="center"
              blockScroll={true}
            >
            <h3>Review</h3>
          {
            
            this.state.Review.map(r => {
              console.log(r.booking.destination.destinationId  +this.state.reviewDestination.destinationId)
              return(
                <div>
                  {
                    r.booking.destination.destinationId  === this.state.reviewDestination.destinationId ?
                    <div className="card card-itenary">
                    <div className="card-body"> 
                      <ul>
                         <li>
                           <div>
                             { noReview = false}
                            <text>{r.booking.destination.userName}</text>
                           <Rating value={r.rating}  readonly stars={5} cancel={false}/>
                           <text> {r.review}</text>
                           </div>
                           </li>
                      </ul>
                    </div>
                  </div>
                 
                    :null
                                      }
                </div>
              )

            })
            
          }
          {noReview ? <h1>No Review till Now </h1> : null}
            </Dialog>

          <Dialog header="Itinerary" visible={this.state.showModel} model={true} maximizable={true}
              contentStyle={{ maxHeight: "500px", overflow: "auto" }}
              contentClassName="p-dialog p-dialog-title"
              onHide={this.hideShow}
              position="center"
              blockScroll={true}
            >
            <div className="bg-modal2">
          <div className="modal-content2">
            <div className="card">
              {this.state.packageDetails.map(y => {
                if (this.state.itenary === y.destinationId) {
                  return (<React.Fragment> <div className="card-body">

                    <div className="row">
                      <div className="card card-itenary">
                        <div className="card-body">
                          <h3>Tour Highlights</h3>
                          <ul >
                            {y.detailsDTO.highlights.split(",").map(x => {
                              return <li>{x}</li>
                            })}


                          </ul>

                        </div>

                      </div>
                      <div className="card card-itenary">
                        <div className="card-body">
                          <img src={y.imageUrl} alt="" className="img-adj"></img>

                        </div>
                      </div>

                    </div>
                    <div className="row">
                      <div className="card card-itenary">
                        <div className="card-body bg-img">


                        </div>
                      </div>
                      <div className="card card-itenary">
                        <div className="card-body">
                          <h3>Package Inclusuions</h3>
                          <ul >
                            {y.detailsDTO.packageInclusion.split(",").map(x => {
                              return <li>{x}</li>

                            })}

                          </ul>

                        </div>
                      </div>


                    </div>
                  </div>
                    <div className="tour-mod2"> Price starting from <span>:${y.chargePerPerson}</span></div>
                    <div className="itenary-mod">
                      <div className="tour-mod">Tour Pace</div>
                      <ul>
                        <div>{y.detailsDTO.pace.split(",", 1).map(x => {
                          return <li>

                            {x}

                          </li>


                        })}</div>
                        <li>{y.detailsDTO.pace.substring(y.detailsDTO.pace.indexOf(",") + 1)}</li></ul>
                        

                    </div>
                    <div className="card-footer">
                      <div >
                        <Link to={"/booking/" + y.destinationId}>
                          <button className="btn btn-primary btn-adj" onClick={() => <Redirect to={"/booking/" + y.destinationId} />}>
                            book
            </button>

                        </Link>
                        
                  

                   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
                   &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 


                        <button className="btn btn-danger btn-adj1" onClick={this.hideShow}>
                          Cancel
          </button>

                      </div>



                  


                    </div>



                  </React.Fragment>
                  )
                }
              })}




            </div>
          </div></div>          
            </Dialog>
        <div >
          <div className="col-lg-10 offset-1 ">
            <form className="card bg-white ">
              <div className="card-body container">
               <h2 className="font-italic text-center text-primary" > LET'S EXPLORE THE WORLD</h2>
               

              </div>
            </form>
          </div>
          { this.state.packageDetails.map(x => this.view(x))}
          {(this.state.errorMessage) ? <h1 className="text-center">{this.state.errorMessage}</h1> : ""}
        </div> 
          </div> 
    )

  }
}



export default Search
