##General vars
variable "ssh_user" {
  default = "ubuntu"
}
variable "public_key_path" {
  default = "your/path/to/your/public/keys/"
}
variable "private_key_path" {
  default = "your/path/to/your/private/keys/"
}

##AWS Specific Vars
variable "aws_worker_count" {
  default = 4
}
variable "aws_key_name" {
  default = "microservices-ssh-key"
}
variable "aws_instance_size" {
  default = "t2.micro"
}
variable "aws_region" {
  default = "eu-west-2"
}
