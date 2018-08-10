var dpSelf = document.getElementById("dp-self");
function uploadFile(file,path){
    var filename = file.name;
    // var uploadTask = storageRef.child(path+'/'+filename).put(file);
    console.log(filename+"  "+path);
    
}

dpSelf.addEventListener("change",function (e) {
    var selectedFile = e.target.files[0];
    // get path from the type of html modal
    // path = displayPic,friendPic,likePic,interestPic
    var path = "displayPic";
    uploadFile(selectedFile,path);
})