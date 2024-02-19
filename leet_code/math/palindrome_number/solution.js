/**
 * @param {number} x
 * @return {boolean}
 */
var isPalindrome = function(x) {
    let reversed = x.toString().split("").reverse().join("");

    return reversed == x.toString() ? true : false;
};