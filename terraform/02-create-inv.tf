resource "null_resource" "ansible-provision" {
  depends_on = [aws_instance.swarm-master, aws_instance.aws-swarm-members]

  provisioner "local-exec" {
    command = "echo \"[all:vars]\" >> ../ansible/swarm-inventory"
  }

  provisioner "local-exec" {
    command = "echo \"${format("ansible_ssh_private_key_file=%s", var.private_key_path)}\" >>  ../ansible/swarm-inventory"
  }

  provisioner "local-exec" {
    command = "echo \"[swarm-master]\" >> ../ansible/swarm-inventory"
  }

  provisioner "local-exec" {
    command = "echo \"${format("%s ansible_ssh_user=%s", aws_instance.swarm-master.0.public_ip, var.ssh_user)}\" >> ../ansible/swarm-inventory"
  }

  provisioner "local-exec" {
    command = "echo \"[swarm-nodes]\" >> ../ansible/swarm-inventory"
  }

  provisioner "local-exec" {
    command = "echo \"${join("\n",formatlist("%s ansible_ssh_user=%s", aws_instance.aws-swarm-members.*.public_ip, var.ssh_user))}\" >> ../ansible/swarm-inventory"
  }
}
 
