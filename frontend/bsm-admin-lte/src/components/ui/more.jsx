import React, { Component } from 'react'
import ContentHeader from '../../common/template/content/contentHeader';
import Content from '../../common/template/content/content'
import PageHeader from '../../common/template/content/pageHeader'
import Row from '../../common/layout/row'
import Box from '../../common/elements/box'



export default class More extends Component {

 

    render() {

        

        
        return (
            <div>
                <ContentHeader title='Typography' small='version 1.0' />

                <Content>
                    <Row>
                        <Box cols="12 4" title="Bootstrap Heading" icon="edit" primary icon="text-width" >
                        <h1>h1. Bootstrap heading</h1>

                        <h2>h2. Bootstrap heading</h2>

                        <h3>h3. Bootstrap heading</h3>
                        <h4>h4. Bootstrap heading</h4>
                        <h5>h5. Bootstrap heading</h5>
                        <h6>h6. Bootstrap heading</h6>
                        </Box>

                        <Box cols="12 4" title="Text Emphasis" icon="edit" primary icon="text-width" >
                        <p className="lead">Lead to emphasize importance</p>

                        <p className="text-green">Text green to emphasize success</p>

                        <p className="text-aqua">Text aqua to emphasize info</p>

                        <p className="text-light-blue">Text light blue to emphasize info (2)</p>

                        <p className="text-red">Text red to emphasize danger</p>

                        <p className="text-yellow">Text yellow to emphasize warning</p>

                        <p className="text-muted">Text muted to emphasize general</p>
                        </Box>

                        <Box cols="12 4" title="Quote Text" icon="edit" primary icon="text-width" >
                        <blockquote>
                          <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere erat a ante.</p>
                          <small>Someone famous in <cite title="Source Title">Source Title</cite></small>
                        </blockquote>
                        </Box>
                        </Row>
                        <Row>  
                        <Box cols="12 3" title="Unordered List" icon="edit" primary icon="text-width" >
                        <ul>
                          <li>Lorem ipsum dolor sit amet</li>
                          <li>Consectetur adipiscing elit</li>
                          <li>Integer molestie lorem at massa</li>
                          <li>Facilisis in pretium nisl aliquet</li>
                          <li>Nulla volutpat aliquam velit
                            <ul>
                              <li>Phasellus iaculis neque</li>
                              <li>Purus sodales ultricies</li>
                              <li>Vestibulum laoreet porttitor sem</li>
                              <li>Ac tristique libero volutpat at</li>
                            </ul>
                          </li>
                          <li>Faucibus porta lacus fringilla vel</li>
                          <li>Aenean sit amet erat nunc</li>
                          <li>Eget porttitor lorem</li>
                        </ul>
                        </Box>
                        <Box cols="12 3" title="Ordered List" icon="edit" primary icon="text-width" >
                        <ol>
                          <li>Lorem ipsum dolor sit amet</li>
                          <li>Consectetur adipiscing elit</li>
                          <li>Integer molestie lorem at massa</li>
                          <li>Facilisis in pretium nisl aliquet</li>
                          <li>Nulla volutpat aliquam velit
                            <ol>
                              <li>Phasellus iaculis neque</li>
                              <li>Purus sodales ultricies</li>
                              <li>Vestibulum laoreet porttitor sem</li>
                              <li>Ac tristique libero volutpat at</li>
                            </ol>
                          </li>
                          <li>Faucibus porta lacus fringilla vel</li>
                          <li>Aenean sit amet erat nunc</li>
                          <li>Eget porttitor lorem</li>
                        </ol>
                        </Box>
                        <Box cols="12 3" title="Description" icon="edit" primary icon="text-width" >
                        <dl>
                          <dt>Description lists</dt>
                          <dd>A description list is perfect for defining terms.</dd>
                          <dt>Euismod</dt>
                          <dd>Vestibulum id ligula porta felis euismod semper eget lacinia odio sem nec elit.</dd>
                          <dd>Donec id elit non mi porta gravida at eget metus.</dd>
                          <dt>Malesuada porta</dt>
                          <dd>Etiam porta sem malesuada magna mollis euismod.</dd>
                        </dl>
                        </Box>
                        <Box cols="12 3" title="Description Horizontal" icon="edit" primary icon="text-width" >
                        <dl className="dl-horizontal">
                          <dt>Description lists</dt>
                          <dd>A description list is perfect for defining terms.</dd>
                          <dt>Euismod</dt>
                          <dd>Vestibulum id ligula porta felis euismod semper eget lacinia odio sem nec elit.</dd>
                          <dd>Donec id elit non mi porta gravida at eget metus.</dd>
                          <dt>Malesuada porta</dt>
                          <dd>Etiam porta sem malesuada magna mollis euismod.</dd>
                          <dt>Felis euismod semper eget lacinia</dt>
                          <dd>Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo
                            sit amet risus.
                          </dd>
                        </dl>
                        </Box>
                        </Row>
                    
                    <PageHeader title="Accordion & Carousel" />
                    <Row>
                    <Box cols="12 6" title="Carousel" icon="edit" primary icon="text-width" >
                      <div id="carousel-example-generic" className="carousel slide" data-ride="carousel">
                      <ol className="carousel-indicators">
                        <li data-target="#carousel-example-generic" data-slide-to={0} className="active" />
                        <li data-target="#carousel-example-generic" data-slide-to={1} className />
                        <li data-target="#carousel-example-generic" data-slide-to={2} className />
                      </ol>
                      <div className="carousel-inner">
                        <div className="item active">
                          <img src="http://placehold.it/900x500/39CCCC/ffffff&text=I+Love+Bootstrap" alt="First slide" />
                          <div className="carousel-caption">
                            First Slide
                          </div>
                        </div>
                        <div className="item">
                          <img src="http://placehold.it/900x500/3c8dbc/ffffff&text=I+Love+Bootstrap" alt="Second slide" />
                          <div className="carousel-caption">
                            Second Slide
                          </div>
                        </div>
                        <div className="item">
                          <img src="http://placehold.it/900x500/f39c12/ffffff&text=I+Love+Bootstrap" alt="Third slide" />
                          <div className="carousel-caption">
                            Third Slide
                          </div>
                        </div>
                      </div>
                      <a className="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                        <span className="fa fa-angle-left" />
                      </a>
                      <a className="right carousel-control" href="#carousel-example-generic" data-slide="next">
                        <span className="fa fa-angle-right" />
                      </a>
                    </div>
                    </Box>
                    <Box cols="12 6" title="Accordion" icon="edit" primary icon="text-width" >
                    <div className="box-group" id="accordion">
        {/* we are adding the .panel class so bootstrap.js collapse plugin detects it */}
        <div className="panel box box-primary">
          <div className="box-header with-border">
            <h4 className="box-title">
              <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" style={{ textDecoration: 'none' }}>
                Collapsible Group Item #1
              </a>
            </h4>
          </div>
          <div id="collapseOne" className="panel-collapse collapse in">
            <div className="box-body">
              Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3
              wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum
              eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla
              assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred
              nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer
              farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus
              labore sustainable VHS.
            </div>
          </div>
        </div>
        <div className="panel box box-danger">
          <div className="box-header with-border">
            <h4 className="box-title">
              <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" style={{ textDecoration: 'none' }}>
                Collapsible Group Danger
              </a>
            </h4>
          </div>
          <div id="collapseTwo" className="panel-collapse collapse">
            <div className="box-body">
              Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3
              wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum
              eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla
              assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred
              nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer
              farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus
              labore sustainable VHS.
            </div>
          </div>
        </div>
        <div className="panel box box-success">
          <div className="box-header with-border">
            <h4 className="box-title">
              <a data-toggle="collapse" data-parent="#accordion" href="#collapseThree" style={{ textDecoration: 'none' }}>
                Collapsible Group Success
              </a>
            </h4>
          </div>
          <div id="collapseThree" className="panel-collapse collapse">
            <div className="box-body">
              Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3
              wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum
              eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla
              assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred
              nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer
              farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus
              labore sustainable VHS.
            </div>
          </div>
        </div>
      </div>

                    </Box>
                    </Row>     
                    <PageHeader title="Forms" />
                    <PageHeader title="More" />
                    
                    
                    
                
                   
                    <PageHeader />


                </Content>
            </div >
        )
    }
}