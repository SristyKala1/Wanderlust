import Carousel from 'react-bootstrap/Carousel'
import React, {Component} from 'react'
export class CarouselTour extends Component{
    render(){
        return(
            <div>
                <div class='container-fluid'>
                <div className="row title" style={{ marginBottom: "20px" }}>
                <div class="col-sm-12 btn btn-primary">
               WHAT DO YOU THINK ABOUT TRAVELLING?
                </div>
                </div>
                </div>
                <div class='container-fluid'>
                <Carousel>
                    <Carousel.Item style={{'height':"600px"}}>
                    <img style={{'height':"600px"}} alt=""className="d-block w-100" src={'assets/tokyo.jpg'} />
                    <Carousel.Caption>
                        <h5>THE WORLD IS A BOOK AND THOSE WHO DO NOT TRAVEL READ ONLY A PAGE</h5>
                        <p> --SAINT AUGUSTINE</p>
                        </Carousel.Caption>
                        </Carousel.Item>

                    <Carousel.Item style={{'height':"600px"}}>
                    <img style={{'height':"600px"}}alt="" className="d-block w-100" src={'assets/bg-masthead1.jpg'} />
                    <Carousel.Caption>
                        <h5>LIFE IS EITHER A DARING ADVENTURE OR NOTHING AT ALL</h5>
                        <p> --HELEN KELLER</p>
                        </Carousel.Caption>
                        </Carousel.Item>

                        
                        <Carousel.Item style={{'height':"600px"}}>
                        <img style={{'height':"600px"}} alt="" className="d-block w-100" src={'assets/india.jpg'} />
                    <Carousel.Caption>
                        <h5>TRAVEL MAKES ONE MODEST,YOU SEE WHAT A TINY PLACE YOU OCCUPY IN THE WORLD</h5>
                        <p> --GUSTSVE FLAUBERT</p>
                        </Carousel.Caption>
                        </Carousel.Item>
                        </Carousel>
                        </div>
            </div>
        )
    }
}
export default CarouselTour