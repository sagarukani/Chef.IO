const path = require('path');
const multer = require('multer');
const util = require('util');

global.__basedir = path.resolve(__dirname);

function sanitizeFilename(filename) {
    // Replace non-breaking space with regular space and remove other special characters
    return filename.replace(/\s/g, ' ').replace(/[^a-zA-Z0-9. ]/g, '');
}

let storage = multer.diskStorage({
    destination: (req, file, cb) => {
        cb(null, path.join(__basedir, "/assets/uploads/"));
    },
    filename: (req, file, cb) => {
        cb(null, file.originalname.replaceAll(" ", "")/*md5(Date.now()) + path.extname(file.originalname)*/);
    },
});

let uploadFile = multer({
    storage: storage,
}).single("file");

let uploadFileMiddleware = util.promisify(uploadFile);
module.exports = uploadFileMiddleware;