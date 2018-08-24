var database = firebase.database();
var storage = firebase.storage();
var storageRef = storage.ref();

function saveURLToDB(url) {
    //save URL to db
    console.log(url);
    
}

function uploadFile(file,path){
    // give a better name or folder structure to db
    var filename = new Date() + file.name;
    var uploadTask = storageRef.child(path+'/'+filename).put(file);

    uploadTask.then(function (snapshot) {
        snapshot.ref.getDownloadURL().then(function (url) {
            saveURLToDB(url);
        })
    });
}