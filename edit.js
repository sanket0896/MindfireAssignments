// initialise session storage or local storage

let wStorage = window.localStorage;

let personDetails = {
    "personalDetails" : {
        "dpURLSelf" : "#",
        "name": "",
        "dob": "",
        "city": "",
        "company": "",
        "facebookURL": "",
        "twitterURL": "",
        "githubURL": "",
        "linkedinURL": ""
    },
    "bioDetails" : "",
    "friendDetails" : [],
    "likeDetails" : [],
    "interestDetails" : []
};

function getDataFromStorage(key) {
    if (wStorage.getItem(key)) {
        value = JSON.parse(wStorage.getItem(key));
        return value;
    }
    else{
        return personDetails;
    }
}

//on form submit, create JSON object and write JSON to session

$("#modal-form-dp").on("submit", function (e) {
    e.preventDefault();
    let currentPerson = getDataFromStorage("personDetails");
    currentPerson.personalDetails.dpURLSelf = e.target.elements[0].value;
    // e.target.elements[0].value = "";
    $("#dp-upload-modal").modal('hide');
    wStorage.setItem("personDetails",JSON.stringify(currentPerson));
    return false;
});

$("#page-form-personal-details").on("submit", function (e) {
    e.preventDefault();
    let currentPerson = getDataFromStorage("personDetails");
    currentPerson.personalDetails.name = e.target.elements[0].value;
    // e.target.elements[0].value = "";
    currentPerson.personalDetails.dob = e.target.elements[1].value;
    // e.target.elements[1].value = "";
    currentPerson.personalDetails.city = e.target.elements[2].value;
    // e.target.elements[2].value = "";
    currentPerson.personalDetails.company = e.target.elements[3].value;
    // e.target.elements[3].value = "";
    currentPerson.personalDetails.facebookURL = e.target.elements[4].value;
    // e.target.elements[4].value = "";
    currentPerson.personalDetails.twitterURL = e.target.elements[5].value;
    // e.target.elements[5].value = "";
    currentPerson.personalDetails.githubURL = e.target.elements[6].value;
    // e.target.elements[6].value = "";
    currentPerson.personalDetails.linkedinURL = e.target.elements[7].value;
    // e.target.elements[7].value = "";
    console.log(currentPerson);
    wStorage.setItem("personDetails",JSON.stringify(currentPerson));
    return false;
});

$("#page-form-bio").on("submit", function (e) {
    e.preventDefault();
    let currentPerson = getDataFromStorage("personDetails");
    currentPerson.bioDetails = e.target.elements[0].value;
    console.log(currentPerson);
    wStorage.setItem("personDetails",JSON.stringify(currentPerson));
    return false;
})

$("#modal-form-friend").on("submit", function (e) {
    e.preventDefault();
    let currentPerson = getDataFromStorage("personDetails");
    let currentFriend = {
        "imageURL": e.target.elements[0].value,
        "name": e.target.elements[1].value
    }
    currentPerson.friendDetails.push(currentFriend);
    e.target.elements[0].value = "";
    e.target.elements[1].value = "";
    $("#friend-upload-modal").modal('hide');
    console.log(currentPerson);    
    wStorage.setItem("personDetails",JSON.stringify(currentPerson));
    return false;
});

$("#modal-form-like").on("submit", function (e) {
    e.preventDefault();
    let currentPerson = getDataFromStorage("personDetails");
    let currentLike = {
        "imageURL": e.target.elements[0].value,
        "name": e.target.elements[1].value
    }
    currentPerson.likeDetails.push(currentLike);
    e.target.elements[0].value = "";
    e.target.elements[1].value = "";
    $("#like-upload-modal").modal('hide');
    console.log(currentPerson);    
    wStorage.setItem("personDetails",JSON.stringify(currentPerson));
    return false;
});

$("#modal-form-interest").on("submit", function (e) {
    e.preventDefault();
    let currentPerson = getDataFromStorage("personDetails");
    let currentInterest = {
        "imageURL": e.target.elements[0].value,
        "name": e.target.elements[1].value
    }
    currentPerson.interestDetails.push(currentInterest);
    e.target.elements[0].value = "";
    e.target.elements[1].value = "";
    $("#interest-upload-modal").modal('hide');
    console.log(currentPerson);    
    wStorage.setItem("personDetails",JSON.stringify(currentPerson));    
    return false;
});

document.getElementById("delete-user").addEventListener("click",function () {
    wStorage.removeItem("personDetails");
    alert("User deleted. Please fill the details again.");
    window.open("/edit.html","_self");
})