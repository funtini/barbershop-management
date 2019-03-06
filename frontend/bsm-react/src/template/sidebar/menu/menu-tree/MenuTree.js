import React, {Component} from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

//components
import joinClassNames from 'shared/utils/joinClassNames';

//styles
import styles from './MenuTree.css';

class MenuTree extends Component {
    constructor(props) {
        super(props);

        this.state = {
            isOpen: false
        };
    }


    componentWillReceiveProps(nextProps) {
        if (!nextProps.selected){
            this.setState({
                isOpen: false
            })
        }
    }


    render() {
        const { path, icon, label, children, ...rest } = this.props;
        const { isOpen } = this.state;
        const arrowClass = isOpen ? styles.open : styles.close;
        const subMenuClass = joinClassNames(
            styles.subMenu,
            isOpen && styles.expand,
            !isOpen && styles.collapse
        );

        return (
            <li className={ styles.treeView } >
                <a href={ path }  className={ isOpen ? styles.active : undefined } onClick={ this._handleClick } >
                    <FontAwesomeIcon icon={ icon } className={ styles.icon }/>
                    <span>{ label }</span>
                    <span className={ styles.arrow }>
                <FontAwesomeIcon icon={'angle-left'} className={ arrowClass } />
          </span>
                </a>
                <ul className={ subMenuClass }>
                    { children }
                </ul>
            </li>
        );
    }

    _handleClick = () => {
        this.props.onClick(this.props.label);
        this.setState(prevState => ({
            isOpen: !prevState.isOpen
        }))
    };

}

export default MenuTree;
