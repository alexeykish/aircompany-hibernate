/**
 * @author Kish Alexey
 */
function check(formID) {
    var pattern = null;

    switch (formID) {
        case 'first_name':
            pattern = new RegExp('(^[A-Z]?[a-z]{1,15}$)|(^[А-Я]?[а-я]{1,15}$)', '');
            break;

        case 'last_name':
            pattern = new RegExp('(^[A-Z]?[a-z]{1,15}$)|(^[А-Я]?[а-я]{1,15}$)', '');
            break;

        case 'login':
            pattern = new RegExp('(^[a-zA-Z0-9]{3,15}$)', '');
            break;

        case 'password':
            pattern = new RegExp('^[a-zA-Z0-9_\\*\\!\\^]{6,15}$', '');
            break;

        case 'email':
            pattern = new RegExp('^[a-z0-9\\.]{3,25}@[a-z\\.]{3,10}\\.{1}[a-z]{2,5}$', '');
            break;

    }

    var inputStr = document.getElementById(formID + 'Form').value;

    if (pattern.test(inputStr)) {
        document.getElementById(formID).style.color = 'green';
    } else {
        document.getElementById(formID).style.color = 'red';
    }
}

function clearField(msg, formID) {
    var str = document.getElementById(formID).value;
    if (str == msg)
        document.getElementById(formID).value = '';
}

function restoreField(msg, formID) {
    var str = document.getElementById(formID).value;
    if (str == '')
        document.getElementById(formID).value = msg;
}

