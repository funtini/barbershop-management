import React from 'React'
import Grid from '../layout/grid'

export default props => (
    <Grid cols={props.cols}>
         {props.style==="2" ?
         <div className="box box-widget widget-user">
         <div className={`widget-user-header bg-${props.color}`}>
             <h3 className="widget-user-username">{props.name}</h3>
             <h5 className="widget-user-desc">{props.role}</h5>
         </div>
         <div className="widget-user-image">
         <img className="img-circle" src={require('../../images/user1-128x128.jpg')} alt="User Avatar"/>
         </div>

       <div className="box-footer">
           <div className="row">
             <div className="col-sm-4 border-right">
               <div className="description-block">
                 <h5 className="description-header">3,200</h5>
                 <span className="description-text">SALES</span>
               </div>

             </div>

             <div className="col-sm-4 border-right">
               <div className="description-block">
                 <h5 className="description-header">13,000</h5>
                 <span className="description-text">FOLLOWERS</span>
               </div>

             </div>

             <div className="col-sm-4">
               <div className="description-block">
                 <h5 className="description-header">35</h5>
                 <span className="description-text">PRODUCTS</span>
               </div>

             </div>

           </div>

         </div>
       </div>
    :
    <div className="box box-widget widget-user-2">
            <div className={`widget-user-header bg-${props.color}`}>
                <div className="widget-user-image">
                    <img className="img-circle" src={require('../../images/user1-128x128.jpg')} alt="User Avatar"/>
                </div>
                <h3 className="widget-user-username">{props.name}</h3>
                <h5 className="widget-user-desc">{props.role}</h5>
            </div>
            

          <div className="box-footer no-padding">
            <ul className="nav nav-stacked">
                    <li><a href="#">Projects <span className="pull-right badge bg-blue">31</span></a></li>
                    <li><a href="#">Tasks <span className="pull-right badge bg-aqua">5</span></a></li>
                    <li><a href="#">Completed Projects <span className="pull-right badge bg-green">12</span></a></li>
                    <li><a href="#">Followers <span className="pull-right badge bg-red">842</span></a></li>
                </ul>
         </div>
          </div> } 
        
        
    </Grid>
)