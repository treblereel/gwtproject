/**
 * @externs
 */
 
/**
* @return {number}
*/
var getTimestamp = function() {};
 
/**
* @return {string}
*/
var getTimezone = function() {};
 
/**
* @param {number} milliseconds
*/
var sleep = function(milliseconds) {};
 
/**
* @return {string}
* @param {boolean} daylight
* @param {string} timeZone
* @param {string} style
* @param {string} locale
*/
var displayTimeZone = function(daylight, timeZone, style, locale) {};
 
/**
* @return {Array<string>}
* @param {string} style
* @param {boolean} standalone
* @param {string} locale
*/
var displayMonths = function(style, standalone, locale) {};
 
/**
* @return {Array<string>}
* @param {string} style
* @param {boolean} standalone
* @param {string} locale
*/
var displayWeekdays = function(style, standalone, locale) {};
 
/**
* @return {Array<string>}
* @param {string} style
* @param {string} locale
*/
var displayEras = function(style, locale) {};
 
/**
* @return {Array<string>}
* @param {string} style
* @param {string} locale
*/
var displayAmpm = function(style, locale) {};
 
/**
* @return {!DecimalProperty}
* @param {string} locale
*/
var displayNumber = function(locale) {};
 
/**
* @return {Array<string>}
* @param {Array<string>} locales
*/
var supportedLocalesOfDateTimeFormat = function(locales) {};
 
/**
* @return {Array<string>}
* @param {Array<string>} locales
*/
var supportedLocalesOfNumberFormat = function(locales) {};
 
/**
 * @record
 */
var DecimalProperty;
 
/** @type {string} */ DecimalProperty.prototype.zeroDigit;
/** @type {string} */ DecimalProperty.prototype.positiveSign;
/** @type {string} */ DecimalProperty.prototype.negativeSign;
/** @type {string} */ DecimalProperty.prototype.decimalSeparator;
 
var Base64Binary = function() {}
 
/**
 * @param {string} base64
 * @return {ArrayBuffer}
 */
Base64Binary.decodeArrayBuffer = function(base64) {};
 
/**
 * @param {string} base64
 * @return {Uint8Array}
 */
Base64Binary.decode = function(base64) {};