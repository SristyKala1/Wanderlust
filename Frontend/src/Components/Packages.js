import React, { Component } from "react";
import axios from "axios";
import { backendUrlPackages, backendUrlViewReview } from "../BackendURL";
import "../custom.css";
import { Link, Redirect } from "react-router-dom";
import { Dialog } from "primereact/dialog"
import { Rating } from 'primereact/rating';


class Packages extends Component {

  constructor() {
    super()
    this.state = {
      hotDeals: [],
      Review : [],
      errorMessage: "",
      sucessMessage: "",
      showModel: false,
      itinerary: "",
      reviewShowModel : false,
      reviewDestination : "",
      value:""
    }
  }

  componentWillMount = () => {
    axios
      .get(backendUrlPackages) //geting package details from db
      .then(response => {
        this.setState({ hotDeals: response.data });
      })
      .catch(error => {
        if (error.response) {
          this.setState({ errorMessage: error.response.data.message });
        } else {
          this.setState({ errorMessage: "Server Down" });
        }
      });

      axios
      .get(backendUrlViewReview)
      .then(response =>{
        this.setState({ Review: response.data });
      }).catch(error =>{
        if (error.response) {
          this.setState({ errorMessage: error.response.data.message });
        } else {
          this.setState({ errorMessage: "Server Down" });
        }
      })
  };

  setShow = event => {
    this.setState({ itinerary: event.target.value, showModel: true });

  };
  hideShow = () => {
    this.setState({ showModel: false, itinerary: "" });
  };

  reviewSetShow = (destination) =>{

    this.setState({ reviewDestination: destination, reviewShowModel: true });

  }

  reviewHideShow = () =>{
    this.setState({ reviewDestination: "", reviewShowModel: false });
  }



  render() {
    var noReview = true
    return (<React.Fragment>
      <div className="package-bg">
      <div className="bg-modal2">
        <div className="modal-content2">
          <div className="card bg-info">



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
                console.log(r.booking.destination.destinationId +
                                           this.state.reviewDestination.destinationId)
             // console.log(r.booking.destination.destinationId + this.state.reviewDestination.destinationId)
              return(
                <div>
                  {
                    r.booking.destination.destinationId === this.state.reviewDestination.destinationId ?
                    <div className="card card-itenary">
                    <div className="card-body"> 
                      <ul>
                         <li>
                           <div>
                             { noReview = false}
                            <text>{r.booking.user.userName}</text>
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
          {noReview ? <h1>No Review Done till</h1> : null}
            </Dialog>
            
            <Dialog header="Itinerary" visible={this.state.showModel} model={true} maximizable={true}
              contentStyle={{ maxHeight: "500px", overflow: "auto" }}
              contentClassName="p-dialog p-dialog-title"
              onHide={this.hideShow}
              position="center"
              blockScroll={true}
            > {/* fetching details based on destinationId & displaying */}
            

              {this.state.hotDeals.map(y => {
                if (this.state.itinerary === y.destinationId) {
                  return (
                    <React.Fragment>
                      {" "}
                      <div className="card-body ">
                        <div className="row">
                          <div className="card card-itenary">
                            <div className="card-body">
                              <h3>Tour Highlights</h3>
                              <ul>
                                {y.detailsDTO.highlights.split(",").map(x => {
                                  return <li>{x}</li>;
                                })}
                              </ul>
                            </div>
                          </div>
                          <div className="card card-itenary">
                            <div className="card-body">
                              <img
                                src={y.imageUrl} alt=""
                                className="img-adj"
                              ></img>
                            </div>
                          </div>
                        </div>
                        <div className="row">
                          <div className="card card-itenary">
                            <div className="card-body ">
                              <img
                                src="./assets/europe.jpg" alt=""
                                className="img-adj"
                              ></img>
                            </div>
                          </div>
                          <div className="card card-itenary">
                            <div className="card-body">
                              <h3>Package Inclusions</h3>
                              <ul>
                                {y.detailsDTO.packageInclusion
                                  .split(",")
                                  .map(x => {
                                    return <li>{x}</li>;
                                  })}
                              </ul>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div className="tour-mod2 bg-light text-danger font-weight-bold">
                        {" "}
                        Price starting from <span>:${y.chargePerPerson}</span>
                      </div>
                      <div className="itenary-mod bg-light">
                        <div className="tour-mod">Tour Pace</div>
                        <ul>
                          <div>
                            {y.detailsDTO.pace.split(",", 1).map(x => {
                              return <li>{x}</li>;
                            })}
                          </div>
                          <li>
                            {y.detailsDTO.pace.substring(
                              y.detailsDTO.pace.indexOf(",") + 1
                            )}
                          </li>
                        </ul>
                      </div>
                      <div className="card-footer">
                     
                        <div>
                          <Link to={"/booking/" + y.destinationId}>
                            <button
                            //style={float-left}
                              className="btn btn-dark"
                              onClick={() => (
                                <Redirect to={"/booking/" + y.destinationId} />
                              )}
                            >
                              Book
                       </button>{" "}
                       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      </Link>
                     
                          <button
                            className="btn btn-danger "
                            onClick={this.hideShow}
                          >
                            Cancel
                        </button>
                        </div>
                      </div>


                    </React.Fragment>
                  );
                }
              })}
            </Dialog>

          </div>
        </div>
      </div>
             {/* retreving hot deals from db and rendering */}
      <div className="bg-pack">
        <br />
        <h1 className="text-center text-dark">Hot Deals</h1>

        {this.state.hotDeals.map(x => {
          return (
            <div>
              <br />
              <br />
              <div className="row ">
                <img
                  src={x.imageUrl} alt=""
                  className="col-lg-3 offset-1"
                ></img>
                <div className="col-lg-7 col-lg-offset-1">
                  <h3>{x.destinationName}</h3>
                  <b className="text-warning">{x.discount}% Instant Discount</b>
                  <br />

                  {x.detailsDTO.about}

                  <div>
                  
                  <Rating value={x.rating}  readonly stars={5} cancel={false}/>
                  <Link  onClick ={() => this.reviewSetShow(x)}>{x.noOfRating}</Link>
                  
                 
                    <br />
                   
                    <button
                      className="btn btn-info"
                      value={x.destinationId}
                      onClick={this.setShow}
                    >
                      CHECK ITINERARY
                      </button>{" "}
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                      </div>
                </div>
                <br />
              </div>
              <br />
              <br />
            </div>
          );
        })}
      </div>
      </div>

    </React.Fragment>
    );
  }
}
export default Packages;


