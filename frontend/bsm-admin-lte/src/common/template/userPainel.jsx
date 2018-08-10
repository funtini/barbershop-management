import React from 'react'

export default props => (
        <div className="user-panel">
        <div className="pull-left image">
          <img src={require('../../images/user1-128x128.jpg')} className="img-circle" alt="User Image"/>
        </div>
        <div className="pull-left info">
          <p>Pedro Domingos</p>
          <a href="#"><i className="fa fa-circle text-success"></i> Online</a>
        </div>
      </div>
)

