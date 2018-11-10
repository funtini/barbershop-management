import React from 'react';

import './tooltip.css';


export default class Tooltip extends React.Component {
    constructor(props) {
      super(props)
  
      this.state = {
        displayTooltip: false
      }
      this.hideTooltip = this.hideTooltip.bind(this)
      this.showTooltip = this.showTooltip.bind(this)
    }
    
    hideTooltip () {
      this.setState({displayTooltip: false})
      
    }
    showTooltip () {
      this.setState({displayTooltip: true})
    }
  
    render() {
      let message = this.props.message
      let position = this.props.position
      return (
        <span className='ttooltip'
            onMouseLeave={this.hideTooltip}
          >
          {this.state.displayTooltip &&
          <div className={`ttooltip-bubble ttooltip-${position}`}>
            <div className='ttooltip-message'>{message}</div>
          </div>
          }
          <span 
            className='ttooltip-trigger'
            onMouseOver={this.showTooltip}
            >
            {this.props.children}
          </span>
        </span>
      )
    }
  }