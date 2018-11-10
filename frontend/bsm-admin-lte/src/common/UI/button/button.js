import React from 'React'

export default props => (
   props.hide ? 
   null :
    <button 
    type="button" 
    className={`btn ${props.block?'btn-block':null} btn-${props.type} btn-${props.size}
     ${props.disabled?'disabled':''} ${props.pullright?'pull-right':''}`} 
    { ...props }>
    { props.icon ? <i className={`fa fa-${props.icon}`}/> : null }
    { props.children }
    </button>
  
)