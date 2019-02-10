import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var imageView: UIImageView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        let imageUrlPath = "/Applications/mamp/htdocs/image.jpg";
        if FileManager.default.fileExists(atPath: imageUrlPath) {
            let url = NSURL(string: imageUrlPath)
            let data = NSData(contentsOf: url! as URL)
            
            imageView.image = UIImage(data: data! as Data)
        }
        
    }


}

