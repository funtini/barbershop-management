import React from 'react'

/*  Types of headerdrop:

     Icon:
     ->   users  - [aqua]
     ->   warning  - [yellow]
     ->   clock-o - relogio
     ->   user-menu
*/

export default props => (
    <li>
        <a href="#">
            <i className={`fa fa-${props.icon} text-${props.color}`} /> {props.text}
        </a>
    </li>
)