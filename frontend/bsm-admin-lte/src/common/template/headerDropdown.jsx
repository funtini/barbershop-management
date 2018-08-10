import React from 'react'
/*  Types of headerdrop:
     ->   notifications-menu
     ->   messages-menu
     ->   tasks-menu
     ->   user-menu

     Icons:
     ->   bell-o  - notification
     ->   envelope-o  - messages
     ->   tasks-menu
     ->   user-menu

    Badge:
     ->   warning  - yellow
     ->   success  - green
     ->   tasks-menu
     ->   user-menu

     Icon:
     ->   users  - [aqua]
     ->   warning  - [yellow]
     ->   tasks-menu
     ->   user-menu
*/
export default props => (
  

    < li className = {`dropdown ${props.type}`} >
        <a href="#" className="dropdown-toggle" data-toggle='dropdown'>
            <i className={`fa fa-${props.icon}`} />
            <span className={`label label-${props.badge}`}>{props.number}</span>
        </a>
        <ul className="dropdown-menu">
            <li className={`header ${props.centered?'text-center':''}`}>{props.header}</li>
            <li>
                {/* inner menu: contains the actual data */}
                <ul className="menu">
                {props.children}
                </ul>
            </li>
            <li className="footer"><a href="#">{props.footer}</a></li>
        </ul>
 </li >

)

