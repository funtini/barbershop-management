import React, { Component } from 'react'
import ContentHeader from '../../common/template/content/contentHeader';
import Content from '../../common/template/content/content'



export default class Lockscreen extends Component {

    componentWillMount(){
        document.getElementById('bodyHtml').className='hold-transition lockscreen'
      }
        componentWillUnmount(){
        document.getElementById('bodyHtml').className='skin-blue fixed sidebar-mini'
      }


    render() {

        return (
            // <div className="hold-transition lockscreen">
         
                    // <div className="lockscreen-wrapper">
                    //     <div className="lockscreen-logo">
                    //         <a href="../dashboard/dashboard"><b>Admin</b>LTE</a>
                    //     </div>

                    //     <div className="lockscreen-name">John Doe</div>

                    //     <div className="lockscreen-item">

                    //         <div className="lockscreen-image">
                    //             <img src={require('../../images/user1-128x128.jpg')} alt="User Image" />


                    //             <form className="lockscreen-credentials">
                    //                 <div className="input-group">
                    //                     <input type="password" className="form-control" placeholder="password" />

                    //                     <div className="input-group-btn">
                    //                         <button type="button" className="btn"><i className="fa fa-arrow-right text-muted"></i></button>
                    //                     </div>
                    //                 </div>
                    //             </form>


                    //         </div>

                    //         <div className="help-block text-center">
                    //             Enter your password to retrieve your session
                    //         </div>
                    //         <div className="text-center">
                    //             <a href="login.html">Or sign in as a different user</a>
                    //         </div>
                    //         <div className="lockscreen-footer text-center">
                    //             Copyright &copy; 2014-2016 <b><a href="https://adminlte.io" className="text-black">Almsaeed Studio</a></b><br />
                    //             All rights reserved
                    //         </div>
                    //     </div>
                    // </div>
                    
<div className="lockscreen-wrapper">
        <div className="lockscreen-logo">
          <a href="../../index2.html"><b>Admin</b>LTE</a>
        </div>
        {/* User name */}
        <div className="lockscreen-name text-center">John Doe</div>
        {/* START LOCK SCREEN ITEM */}
        <div className="lockscreen-item">
          {/* lockscreen image */}
          <div className="lockscreen-image">
            <img src={require('../../images/user1-128x128.jpg')} alt="User Image" />
          </div>
          {/* /.lockscreen-image */}
          {/* lockscreen credentials (contains the form) */}
          <form className="lockscreen-credentials">
            <div className="input-group">
              <input type="password" className="form-control" placeholder="password" />
              <div className="input-group-btn">
                <button type="button" className="btn"><i className="fa fa-arrow-right text-muted" /></button>
              </div>
            </div>
          </form>
          {/* /.lockscreen credentials */}
        </div>
        {/* /.lockscreen-item */}
        <div className="help-block text-center">
          Enter your password to retrieve your session
        </div>
        <div className="text-center">
          <a href="login.html">Or sign in as a different user</a>
        </div>
        <div className="lockscreen-footer text-center">
          Copyright © 2014-2016 <b><a href="https://adminlte.io" className="text-black">Almsaeed Studio</a></b><br />
          All rights reserved
        </div>
      </div>

      
        )
    }
}