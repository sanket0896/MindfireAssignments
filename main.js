let wStorage = window.localStorage;
let personDetails = wStorage.getItem("personDetails");

function populateDataInElement(elementId,data,isLink=false) {
  let parentNode = document.getElementById(elementId);
  if (Array.isArray(data)) {
    data.forEach(function (dataInstance) {
      let domElement = generateElementNode(elementId,dataInstance)
      parentNode.appendChild(domElement);  
    });
  }
  else if(isLink === true) {
      if (elementId === "dp-self") {
        if (data!=="#") {
          parentNode.src = data;          
        }
      }
      else{
        parentNode.href = data;
      }
      return;
    }
  else{
    parentNode.innerHTML=data;
  }
}

if (personDetails) {
  personDetails = JSON.parse(personDetails);
  populateDataInElement("dp-self", personDetails.personalDetails.dpURLSelf, true);
  populateDataInElement("name-self", personDetails.personalDetails.name);
  populateDataInElement("dob-self", personDetails.personalDetails.dob);
  populateDataInElement("city-self", personDetails.personalDetails.city);
  populateDataInElement("company-self", personDetails.personalDetails.company);
  populateDataInElement("facebook-self", personDetails.personalDetails.facebookURL,true);
  populateDataInElement("twitter-self", personDetails.personalDetails.twitterURL,true);
  populateDataInElement("github-self", personDetails.personalDetails.githubURL,true);
  populateDataInElement("linkedin-self", personDetails.personalDetails.linkedinURL,true);
  populateDataInElement("bio-text", personDetails.bioDetails);
  populateDataInElement("friends-list", personDetails.friendDetails);
  populateDataInElement("likes-list", personDetails.likeDetails);
  populateDataInElement("interests-list", personDetails.interestDetails);
}
else{
  alert("User details doesn't exist in records. Please fill the details first.");
  window.open("/edit.html","_self");
}

document.getElementById("delete-user").addEventListener("click",function () {
  wStorage.removeItem("personDetails");
  alert("User deleted. Please fill the details again.");
  window.open("/edit.html","_self");
})