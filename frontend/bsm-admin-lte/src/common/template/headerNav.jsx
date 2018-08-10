import React from 'react'

export default props => (
    <div className="navbar-custom-menu">
    <ul className="nav navbar-nav">
    {props.children}
    </ul>
  </div>
)
