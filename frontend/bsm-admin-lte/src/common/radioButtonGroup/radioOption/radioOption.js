import React from 'react'

export default (props) => (
    <div className="radio">
        <label>
            <input type="radio" value={props.value} checked={props.checked} onChange={props.onChange}/>
            {props.children}
        </label>
    </div>

)