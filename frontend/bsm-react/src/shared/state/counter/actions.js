import  * as actionTypes from './actionTypes';

export function increment(){
    return { type: actionTypes.INCREMENT_COUNT };
}

export function decrement(){
    return { type: actionTypes.DECREMENT_COUNT };
}
