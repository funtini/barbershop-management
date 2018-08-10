import React, { Component } from 'react'
import ContentHeader from '../../common/template/content/contentHeader';
import Content from '../../common/template/content/content'
import PageHeader from '../../common/template/content/pageHeader'
import Row from '../../common/layout/row'
import Box from '../../common/elements/box'
import Button from '../../common/elements/button'
import ButtonGroup from '../../common/elements/buttonGroup'



export default class Buttons extends Component {

 

    render() {

        

        
        return (
            <div>
                <ContentHeader title='Buttons' small='version 1.0' />

                <Content>
               


                    <Row>
                        <Box cols="12 4" title="Classic Buttons" icon="edit" primary >
                        <Button type="default" name="Default"/>
                        <Button type="default" size="lg" name="Default"/>
                        <Button type="default" size="sm" name="Default"/>
                        <Button type="default" size="xs" name="Default"/>
                        <Button type="default" size="flat" name="Default"/>
                        <Button type="default" disabled name="Default"/>
                 
                        <br/>
                        <br/>
              
                        <Button type="primary" name="Primary"/>
                        <Button type="primary" size="lg" name="Primary"/>
                        <Button type="primary" size="sm" name="Primary"/>
                        <Button type="primary" size="xs" name="Primary"/>
                        <Button type="primary" size="flat" name="Primary"/>
                        <Button type="primary" disabled name="Primary"/>
                
                        <br/>
                        <br/>
                  
                        <Button type="success" name="Success"/>
                        <Button type="success" size="lg" name="Success"/>
                        <Button type="success" size="sm" name="Success"/>
                        <Button type="success" size="xs" name="Success"/>
                        <Button type="success" size="flat" name="Success"/>
                        <Button type="success" disabled name="Success"/>
                        
                        <br/>
                        <br/>
                  
                        <Button type="info" name="Info"/>
                        <Button type="info" size="lg" name="Info"/>
                        <Button type="info" size="sm" name="Info"/>
                        <Button type="info" size="xs" name="Info"/>
                        <Button type="info" size="flat" name="Info"/>
                        <Button type="info" disabled name="Info"/>

                        <br/>
                        <br/>
                            <Button type="warning" name="Warning"/>
                            <Button type="warning" size="lg" name="Warning"/>
                            <Button type="warning" size="sm" name="Warning"/>
                            <Button type="warning" size="xs" name="Warning"/>
                            <Button type="warning" size="flat" name="Warning"/>
                            <Button type="warning" disabled name="Warning"/>
                        <br/>
                        <br/>
                            <Button type="danger" name="Danger"/>
                            <Button type="danger" size="lg" name="Danger"/>
                            <Button type="danger" size="sm" name="Danger"/>
                            <Button type="danger" size="xs" name="Danger"/>
                            <Button type="danger" size="flat" name="Danger"/>
                            <Button type="danger" disabled name="Danger"/>
                      
                        </Box>
                        <Box cols="12 8" title="Application Buttons" icon="edit" primary >
                        <p>Add the classes <code>.btn.btn-app</code> to an <code>&lt;a></code> tag to achieve the following:</p>
              <a className="btn btn-app">
                <i className="fa fa-edit"></i> Edit
              </a>
              <a className="btn btn-app">
                <i className="fa fa-play"></i> Play
              </a>
              <a className="btn btn-app">
                <i className="fa fa-repeat"></i> Repeat
              </a>
              <a className="btn btn-app">
                <i className="fa fa-pause"></i> Pause
              </a>
              <a className="btn btn-app">
                <i className="fa fa-save"></i> Save
              </a>
              <a className="btn btn-app">
                <span className="badge bg-yellow">3</span>
                <i className="fa fa-bullhorn"></i> Notifications
              </a>
              <a className="btn btn-app">
                <span className="badge bg-green">300</span>
                <i className="fa fa-barcode"></i> Products
              </a>
              <a className="btn btn-app">
                <span className="badge bg-purple">891</span>
                <i className="fa fa-users"></i> Users
              </a>
              <a className="btn btn-app">
                <span className="badge bg-teal">67</span>
                <i className="fa fa-inbox"></i> Orders
              </a>
              <a className="btn btn-app">
                <span className="badge bg-aqua">12</span>
                <i className="fa fa-envelope"></i> Inbox
              </a>
              <a className="btn btn-app">
                <span className="badge bg-red">531</span>
                <i className="fa fa-heart-o"></i> Likes
              </a>
              <br/>
              <br/>
              <div className="text-center">
                <a className="btn btn-social-icon btn-bitbucket"><i className="fa fa-bitbucket"></i></a>
                <a className="btn btn-social-icon btn-dropbox"><i className="fa fa-dropbox"></i></a>
                <a className="btn btn-social-icon btn-facebook"><i className="fa fa-facebook"></i></a>
                <a className="btn btn-social-icon btn-flickr"><i className="fa fa-flickr"></i></a>
                <a className="btn btn-social-icon btn-foursquare"><i className="fa fa-foursquare"></i></a>
                <a className="btn btn-social-icon btn-github"><i className="fa fa-github"></i></a>
                <a className="btn btn-social-icon btn-google"><i className="fa fa-google-plus"></i></a>
                <a className="btn btn-social-icon btn-instagram"><i className="fa fa-instagram"></i></a>
                <a className="btn btn-social-icon btn-linkedin"><i className="fa fa-linkedin"></i></a>
                <a className="btn btn-social-icon btn-tumblr"><i className="fa fa-tumblr"></i></a>
                <a className="btn btn-social-icon btn-twitter"><i className="fa fa-twitter"></i></a>
                <a className="btn btn-social-icon btn-vk"><i className="fa fa-vk"></i></a>
              </div>





                        </Box>
                    </Row>
                    <PageHeader title="Button Groups" />
                    <Row>
                        <Box cols="12 6" title="Button Group & Split Buttons" icon="warning">
                       <ButtonGroup>
                        <Button type="info" name="Info"/>
                        <Button type="info" name="Info"/>
                        <Button type="info" name="Info"/>
                      </ButtonGroup>
                      <br/>
                      <br/>
                      <ButtonGroup>
                        <Button type="warning" name="Warning"/>
                        <Button type="danger" name="Danger"/>
                        <Button type="info" name="Info"/>
                      </ButtonGroup>
                      <p>Normal split buttons:</p>

              <div className="margin">
                <div className="btn-group">
                  <button type="button" className="btn btn-default">Action</button>
                  <button type="button" className="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    <span className="caret"></span>
                    <span className="sr-only">Toggle Dropdown</span>
                  </button>
                  <ul className="dropdown-menu" role="menu">
                    <li><a href="#">Action</a></li>
                    <li><a href="#">Another action</a></li>
                    <li><a href="#">Something else here</a></li>
                    <li className="divider"></li>
                    <li><a href="#">Separated link</a></li>
                  </ul>
                </div>
                <div className="btn-group">
                  <button type="button" className="btn btn-info">Action</button>
                  <button type="button" className="btn btn-info dropdown-toggle" data-toggle="dropdown">
                    <span className="caret"></span>
                    <span className="sr-only">Toggle Dropdown</span>
                  </button>
                  <ul className="dropdown-menu" role="menu">
                    <li><a href="#">Action</a></li>
                    <li><a href="#">Another action</a></li>
                    <li><a href="#">Something else here</a></li>
                    <li className="divider"></li>
                    <li><a href="#">Separated link</a></li>
                  </ul>
                </div>
                <div className="btn-group">
                  <button type="button" className="btn btn-danger">Action</button>
                  <button type="button" className="btn btn-danger dropdown-toggle" data-toggle="dropdown">
                    <span className="caret"></span>
                    <span className="sr-only">Toggle Dropdown</span>
                  </button>
                  <ul className="dropdown-menu" role="menu">
                    <li><a href="#">Action</a></li>
                    <li><a href="#">Another action</a></li>
                    <li><a href="#">Something else here</a></li>
                    <li className="divider"></li>
                    <li><a href="#">Separated link</a></li>
                  </ul>
                </div>
                <div className="btn-group">
                  <button type="button" className="btn btn-success">Action</button>
                  <button type="button" className="btn btn-success dropdown-toggle" data-toggle="dropdown">
                    <span className="caret"></span>
                    <span className="sr-only">Toggle Dropdown</span>
                  </button>
                  <ul className="dropdown-menu" role="menu">
                    <li><a href="#">Action</a></li>
                    <li><a href="#">Another action</a></li>
                    <li><a href="#">Something else here</a></li>
                    <li className="divider"></li>
                    <li><a href="#">Separated link</a></li>
                  </ul>
                </div>
                </div>
                        </Box>

                        <Box cols="12 6" title="BootStrap Alerts" icon="warning"> 
                                  
                        </Box>

                    </Row>
                    <PageHeader title="Progress Bars" />
                    
                    <PageHeader title="Accordion & Carousel" />
                    
                
                   
                    <PageHeader />


                </Content>
            </div >
        )
    }
}