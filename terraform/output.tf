output "swarm_manager_public_ip" {
    value = "${aws_instance.swarm-master.0.public_ip}"
}
